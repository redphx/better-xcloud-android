package com.betterxcloud.android.managers

import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.webkit.WebView
import com.betterxcloud.android.BuildConfig
import kotlin.math.abs

/**
 * Manages gamepad input and forwards it to the WebView for xCloud streaming
 * Handles both button and analog stick inputs
 */
class GamepadManager(private val webView: WebView) {

    private val gamepadState = mutableMapOf<Int, Float>()

    /**
     * Handle key events from gamepad buttons and D-pad
     */
    fun handleKeyEvent(event: KeyEvent?): Boolean {
        event ?: return false

        val keyCode = event.keyCode
        val action = event.action

        // Check if this is a gamepad event
        if (!isGamepadEvent(event)) {
            return false
        }

        when (action) {
            KeyEvent.ACTION_DOWN -> {
                gamepadState[keyCode] = 1.0f
                injectGamepadButton(keyCode, true)
                return true
            }
            KeyEvent.ACTION_UP -> {
                gamepadState[keyCode] = 0.0f
                injectGamepadButton(keyCode, false)
                return true
            }
        }

        return false
    }

    /**
     * Handle motion events from gamepad analog sticks and triggers
     */
    fun handleMotionEvent(event: MotionEvent?): Boolean {
        event ?: return false

        // Check if this is a gamepad event
        if ((event.source and android.view.InputDevice.SOURCE_JOYSTICK) != android.view.InputDevice.SOURCE_JOYSTICK) {
            return false
        }

        // Left stick (with deadzone)
        val leftX = applyDeadzone(event.getAxisValue(MotionEvent.AXIS_X))
        val leftY = applyDeadzone(event.getAxisValue(MotionEvent.AXIS_Y))

        // Right stick (with deadzone)
        val rightX = applyDeadzone(event.getAxisValue(MotionEvent.AXIS_Z))
        val rightY = applyDeadzone(event.getAxisValue(MotionEvent.AXIS_RZ))

        // Triggers (with deadzone)
        val leftTrigger = applyDeadzone(event.getAxisValue(MotionEvent.AXIS_LTRIGGER))
        val rightTrigger = applyDeadzone(event.getAxisValue(MotionEvent.AXIS_RTRIGGER))

        // D-pad (some controllers use AXIS instead of buttons)
        val hatX = applyDeadzone(event.getAxisValue(MotionEvent.AXIS_HAT_X))
        val hatY = applyDeadzone(event.getAxisValue(MotionEvent.AXIS_HAT_Y))

        injectGamepadAxes(leftX, leftY, rightX, rightY, leftTrigger, rightTrigger, hatX, hatY)

        return true
    }

    /**
     * Check if the event is from a gamepad
     */
    private fun isGamepadEvent(event: KeyEvent): Boolean {
        val source = event.source
        return (source and android.view.InputDevice.SOURCE_GAMEPAD) == android.view.InputDevice.SOURCE_GAMEPAD ||
               (source and android.view.InputDevice.SOURCE_JOYSTICK) == android.view.InputDevice.SOURCE_JOYSTICK
    }

    /**
     * Inject gamepad button press/release into WebView
     */
    private fun injectGamepadButton(keyCode: Int, pressed: Boolean) {
        val buttonIndex = mapKeyCodeToButtonIndex(keyCode)
        if (buttonIndex < 0) return

        val pressedValue = if (pressed) 1.0 else 0.0

        val js = """
            (function() {
                if (window.BX_EXPOSED && window.BX_EXPOSED.updateGamepadState) {
                    window.BX_EXPOSED.updateGamepadState({
                        button: $buttonIndex,
                        value: $pressedValue
                    });
                }
            })();
        """.trimIndent()

        webView.post {
            webView.evaluateJavascript(js, null)
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Button $buttonIndex ${if (pressed) "pressed" else "released"}")
        }
    }

    /**
     * Inject gamepad axes values into WebView
     */
    private fun injectGamepadAxes(
        leftX: Float, leftY: Float,
        rightX: Float, rightY: Float,
        leftTrigger: Float, rightTrigger: Float,
        hatX: Float, hatY: Float
    ) {
        val js = """
            (function() {
                if (window.BX_EXPOSED && window.BX_EXPOSED.updateGamepadState) {
                    window.BX_EXPOSED.updateGamepadState({
                        axes: {
                            leftX: $leftX,
                            leftY: $leftY,
                            rightX: $rightX,
                            rightY: $rightY,
                            leftTrigger: $leftTrigger,
                            rightTrigger: $rightTrigger,
                            hatX: $hatX,
                            hatY: $hatY
                        }
                    });
                }
            })();
        """.trimIndent()

        webView.post {
            webView.evaluateJavascript(js, null)
        }
    }

    /**
     * Map Android key codes to standard gamepad button indices
     * Following the Standard Gamepad layout (similar to Xbox controller)
     */
    private fun mapKeyCodeToButtonIndex(keyCode: Int): Int {
        return when (keyCode) {
            KeyEvent.KEYCODE_BUTTON_A -> 0       // A (Cross on PlayStation)
            KeyEvent.KEYCODE_BUTTON_B -> 1       // B (Circle on PlayStation)
            KeyEvent.KEYCODE_BUTTON_X -> 2       // X (Square on PlayStation)
            KeyEvent.KEYCODE_BUTTON_Y -> 3       // Y (Triangle on PlayStation)
            KeyEvent.KEYCODE_BUTTON_L1 -> 4      // Left Bumper
            KeyEvent.KEYCODE_BUTTON_R1 -> 5      // Right Bumper
            KeyEvent.KEYCODE_BUTTON_L2 -> 6      // Left Trigger
            KeyEvent.KEYCODE_BUTTON_R2 -> 7      // Right Trigger
            KeyEvent.KEYCODE_BUTTON_SELECT -> 8  // Select/Back
            KeyEvent.KEYCODE_BUTTON_START -> 9   // Start/Menu
            KeyEvent.KEYCODE_BUTTON_THUMBL -> 10 // Left Stick Press
            KeyEvent.KEYCODE_BUTTON_THUMBR -> 11 // Right Stick Press
            KeyEvent.KEYCODE_DPAD_UP -> 12       // D-Pad Up
            KeyEvent.KEYCODE_DPAD_DOWN -> 13     // D-Pad Down
            KeyEvent.KEYCODE_DPAD_LEFT -> 14     // D-Pad Left
            KeyEvent.KEYCODE_DPAD_RIGHT -> 15    // D-Pad Right
            KeyEvent.KEYCODE_BUTTON_MODE -> 16   // Guide/Home button
            else -> -1
        }
    }

    /**
     * Apply deadzone to prevent controller drift
     */
    private fun applyDeadzone(value: Float): Float {
        return if (abs(value) < DEADZONE) 0f else value
    }

    companion object {
        private const val TAG = "GamepadManager"
        private const val DEADZONE = 0.15f // 15% deadzone for Fire Stick controllers
    }
}
