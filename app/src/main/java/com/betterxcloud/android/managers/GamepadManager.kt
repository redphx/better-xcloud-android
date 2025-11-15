package com.betterxcloud.android.managers

import android.content.Context
import android.util.Log
import android.view.InputDevice
import android.view.KeyEvent
import android.view.MotionEvent
import android.webkit.WebView
import com.betterxcloud.android.BuildConfig
import kotlin.math.abs

/**
 * Manages gamepad input and forwards it to the WebView for xCloud streaming.
 *
 * This class handles both button and analog stick inputs from game controllers and gamepads,
 * mapping them to standard gamepad button indices and axis values. It communicates with
 * the web application through JavaScript injection to update the gamepad state.
 *
 * The manager tracks the state of all gamepad buttons and axes, applies deadzone filtering
 * to prevent controller drift, and forwards input events to the xCloud web application.
 *
 * @param webView The WebView instance used to inject gamepad state updates via JavaScript
 * @param context Optional context for registering InputDevice listeners (default: null)
 *
 * @see android.view.InputDevice
 * @see android.view.KeyEvent
 * @see android.view.MotionEvent
 */
class GamepadManager(
    private val webView: WebView,
    private val context: Context? = null
) {

    /**
     * Maintains the current state of gamepad buttons and axes.
     * Maps key codes/axis indices to their current float values.
     */
    private val gamepadState = mutableMapOf<Int, Float>()

    /**
     * Input device listener for tracking gamepad connectivity changes.
     * Automatically cleans up gamepad state when devices are disconnected.
     */
    private val inputDeviceListener = InputDevice.InputDeviceListener { deviceId, action ->
        when (action) {
            InputDevice.ACTION_REMOVED -> {
                onGamepadDisconnected(deviceId)
            }
        }
    }

    init {
        // Register InputDevice listener to track gamepad connectivity
        context?.let {
            InputDevice.registerInputDeviceListener(inputDeviceListener, null)
        }
    }

    /**
     * Handles key events from gamepad buttons and D-pad.
     *
     * Processes button press and release events, updating the gamepad state
     * and injecting the button state into the web application.
     *
     * @param event The key event to process, or null (returns false)
     * @return true if the event was handled as a gamepad event, false otherwise
     *
     * @see android.view.KeyEvent
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
     * Handles motion events from gamepad analog sticks and triggers.
     *
     * Processes motion events by extracting axis values for both analog sticks,
     * triggers, and D-pad axes. Applies deadzone filtering to all values and
     * injects the normalized axis values into the web application.
     *
     * @param event The motion event to process, or null (returns false)
     * @return true if the event was handled as a gamepad motion event, false otherwise
     *
     * @see android.view.MotionEvent
     * @see android.view.InputDevice
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
     * Checks if the event is from a gamepad or joystick input device.
     *
     * Verifies that the event source is either GAMEPAD or JOYSTICK to ensure
     * the event should be processed by this manager.
     *
     * @param event The key event to check
     * @return true if the event source is from a gamepad or joystick, false otherwise
     *
     * @see android.view.InputDevice.SOURCE_GAMEPAD
     * @see android.view.InputDevice.SOURCE_JOYSTICK
     */
    private fun isGamepadEvent(event: KeyEvent): Boolean {
        val source = event.source
        return (source and android.view.InputDevice.SOURCE_GAMEPAD) == android.view.InputDevice.SOURCE_GAMEPAD ||
               (source and android.view.InputDevice.SOURCE_JOYSTICK) == android.view.InputDevice.SOURCE_JOYSTICK
    }

    /**
     * Injects a gamepad button press or release event into the WebView.
     *
     * Maps the Android key code to a standard gamepad button index and sends
     * the button state to the web application via JavaScript. The button value
     * is set to 1.0 for pressed and 0.0 for released.
     *
     * @param keyCode The Android key code of the button
     * @param pressed true if the button was pressed, false if released
     *
     * @see mapKeyCodeToButtonIndex
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
     * Injects all gamepad axis values into the WebView.
     *
     * Sends normalized axis values for both analog sticks, triggers, and D-pad axes
     * to the web application via JavaScript. All values are normalized to the range [-1, 1]
     * with deadzone filtering already applied.
     *
     * @param leftX X-axis value for the left analog stick
     * @param leftY Y-axis value for the left analog stick
     * @param rightX X-axis value for the right analog stick
     * @param rightY Y-axis value for the right analog stick
     * @param leftTrigger Analog value for the left trigger
     * @param rightTrigger Analog value for the right trigger
     * @param hatX X-axis value for the D-pad (hat switch)
     * @param hatY Y-axis value for the D-pad (hat switch)
     *
     * @see applyDeadzone
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
     * Maps Android key codes to standard gamepad button indices.
     *
     * Converts Android-specific key codes to standardized gamepad button indices
     * following the Standard Gamepad layout (similar to Xbox controllers).
     * Button indices 0-11 represent the primary buttons, 12-15 represent D-pad,
     * and 16 represents the Guide/Home button.
     *
     * @param keyCode The Android KeyEvent key code to map
     * @return The standard gamepad button index (0-16), or -1 if unmapped
     *
     * @see android.view.KeyEvent
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
     * Applies deadzone filtering to an axis value to prevent controller drift.
     *
     * Returns zero for values that fall within the deadzone threshold, otherwise
     * returns the original value. This prevents small unwanted movements from
     * analog sticks that are in their rest position.
     *
     * @param value The raw axis value to filter
     * @return 0f if the absolute value is less than DEADZONE, otherwise returns the original value
     *
     * @see DEADZONE
     */
    private fun applyDeadzone(value: Float): Float {
        return if (abs(value) < DEADZONE) 0f else value
    }

    /**
     * Handles a gamepad disconnection event.
     *
     * Clears the gamepad state for the disconnected device to prevent stale
     * input values from being processed. This is called when an InputDevice
     * is removed from the system.
     *
     * @param deviceId The ID of the disconnected input device
     */
    private fun onGamepadDisconnected(deviceId: Int) {
        // Clear gamepad state for disconnected device
        gamepadState.clear()
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Gamepad disconnected: $deviceId")
        }
    }

    /**
     * Cleans up resources and unregisters the InputDevice listener.
     *
     * Must be called when the GamepadManager is no longer needed to properly
     * release resources and prevent memory leaks. Unregisters the InputDevice
     * listener that was registered in the init block.
     *
     * Recommended to call in the activity/fragment's onDestroy() method.
     */
    fun cleanup() {
        context?.let {
            try {
                InputDevice.unregisterInputDeviceListener(inputDeviceListener)
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    Log.e(TAG, "Error unregistering input device listener", e)
                }
            }
        }
        gamepadState.clear()
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "GamepadManager cleanup completed")
        }
    }

    companion object {
        /**
         * Logging tag for GamepadManager
         */
        private const val TAG = "GamepadManager"

        /**
         * Deadzone threshold for analog sticks and triggers.
         *
         * Values with absolute value below this threshold are treated as zero.
         * Set to 15% for Fire Stick controllers to minimize drift issues while
         * maintaining responsive input.
         *
         * Valid range: 0.0 - 1.0
         */
        private const val DEADZONE = 0.15f
    }
}
