if (!window.location.pathname.includes('/play') && !window.location.pathname.includes('/auth')) {
    throw new Error('[Better xCloud] Invalid path');
}

const bxFlags = [[BX_FLAGS]];
window.BX_FLAGS = Object.assign({}, bxFlags);

// Patch navigator.getBattery() API in Mulch WebView
if (bxFlags.DeviceInfo.androidInfo.webview.includes('us.spotco.mulch_wv')) {
    const nativeGetBattery = window.navigator.getBattery.bind(window.navigator);
    window.navigator.getBattery = async () => {
        const battery = await nativeGetBattery();
        const status = JSON.parse(AppInterface.getBatteryStatus());

        return new Promise(resolve => resolve({
            charging: status.charging,
            level: status.level,

            chargingTime: battery.chargingTime,
            dischargingTime: battery.dischargingTime,
            onchargingchange: battery.onchargingchange,
            onchargingtimechange: battery.onchargingtimechange,
            ondischargingtimechange: battery.ondischargingtimechange,
            onlevelchange: battery.onlevelchange,
        }));
    }
}

// Map Back button as Guide button
class BackButtonRemap {
    static #orgGetGamepads = window.navigator.getGamepads.bind(window.navigator);
    static #timestamp = 0;
    static #lastButtons = '';

    static #copyButtons(gamepad) {
        return gamepad.buttons.map(button => ({
            pressed: button.pressed,
            value: button.value,
        }));
    }

    static #patchGamepadButtons(gamepad, buttons) {
        let hsh = '';
        for (const button of buttons) {
            hsh += button.value.toString();
        }

        if (hsh !== BackButtonRemap.#lastButtons) {
            BackButtonRemap.#lastButtons = hsh;
            BackButtonRemap.#timestamp = performance.now();
        }

        return {
            id: gamepad.id,
            index: 0,
            connected: true,
            hapticActuators: gamepad.hapticActuators,
            mapping: 'standard',

            axes: [0, 0, 0, 0],
            buttons: buttons,
            timestamp: BackButtonRemap.#timestamp,

            vibrationActuator: gamepad.vibrationActuator,
        };
    }

    static onPress() {
        const gamepads = BackButtonRemap.#orgGetGamepads();

        let mainGamepad = gamepads[0];
        if (!mainGamepad || !mainGamepad.connected) {
            return gamepads;
        }

        const buttons = BackButtonRemap.#copyButtons(mainGamepad);

        // Press the Home button
        buttons[16] = {
            pressed: true,
            value: 1,
        }

        mainGamepad = BackButtonRemap.#patchGamepadButtons(mainGamepad, buttons);
        return [mainGamepad, gamepads[1], gamepads[2], gamepads[3]];
    }

    static onRelease() {
        window.navigator.getGamepads = BackButtonRemap.#orgGetGamepads;
        const gamepads = BackButtonRemap.#orgGetGamepads();

        let mainGamepad = gamepads[0];
        if (!mainGamepad || !mainGamepad.connected) {
            BackButtonRemap.#lastButtons = '';
            return gamepads;
        }

       const buttons = BackButtonRemap.#copyButtons(mainGamepad);
       mainGamepad = BackButtonRemap.#patchGamepadButtons(mainGamepad, buttons);

       BackButtonRemap.#lastButtons = '';
       return [mainGamepad, gamepads[1], gamepads[2], gamepads[3]];
    }

    static setup() {
        window.backButtonDown = function() {
            window.navigator.getGamepads = BackButtonRemap.onPress;
        }

        window.backButtonUp = function() {
            window.navigator.getGamepads = BackButtonRemap.onRelease;
        }
    }
}

[[REMAP_BACK_BUTTON]] && BackButtonRemap.setup();
