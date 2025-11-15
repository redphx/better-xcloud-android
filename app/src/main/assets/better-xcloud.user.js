// ==UserScript==
// @name         Better xCloud (Android TV Port)
// @description  Improve Xbox Cloud Gaming (xCloud) experience on Android TV
// @version      0.1.0
// @match        https://www.xbox.com/*/play*
// @match        https://www.xbox.com/play*
// @grant        none
// @run-at       document-start
// ===/UserScript==

/**
 * Better xCloud - Android TV Port
 *
 * This is a placeholder for the Better xCloud userscript.
 * The full script should be fetched from:
 * https://github.com/redphx/better-xcloud
 *
 * To use the actual Better xCloud script:
 * 1. Download the latest better-xcloud.user.js from the GitHub repo
 * 2. Replace this file with the downloaded script
 * 3. Rebuild the app
 *
 * For now, this placeholder provides basic Android TV integration.
 */

(function() {
    'use strict';

    console.log('[Better xCloud] Android TV version initialized');

    // Expose interface for native Android code
    window.BX_EXPOSED = window.BX_EXPOSED || {};

    // Gamepad state management
    window.BX_EXPOSED.updateGamepadState = function(state) {
        // This will be called from native Android code with gamepad events
        if (state.button !== undefined) {
            console.log(`[Better xCloud] Button ${state.button}: ${state.value}`);
        }
        if (state.axes) {
            console.log(`[Better xCloud] Axes update:`, state.axes);
        }
        // TODO: Inject into actual gamepad API
    };

    // Notify Android that we're ready
    if (window.AndroidInterface) {
        window.AndroidInterface.log('[Better xCloud] Script loaded successfully');
        window.AndroidInterface.log('[Better xCloud] Device: ' + window.AndroidInterface.getDeviceInfo());
    }

    // Basic optimizations for TV
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', function() {
            applyTVOptimizations();
        });
    } else {
        applyTVOptimizations();
    }

    function applyTVOptimizations() {
        console.log('[Better xCloud] Applying TV optimizations');

        // Add custom CSS for TV interface
        const style = document.createElement('style');
        style.textContent = `
            /* Better xCloud TV Optimizations */
            body {
                cursor: none !important;
            }

            /* Improve focus visibility for D-pad navigation */
            *:focus {
                outline: 3px solid #107C10 !important;
                outline-offset: 2px !important;
            }

            /* Hide mouse cursor during gameplay */
            #game-stream {
                cursor: none !important;
            }
        `;
        document.head.appendChild(style);

        // Log stream events
        observeStreamEvents();
    }

    function observeStreamEvents() {
        // Watch for stream container
        const observer = new MutationObserver(function(mutations) {
            mutations.forEach(function(mutation) {
                mutation.addedNodes.forEach(function(node) {
                    if (node.id === 'game-stream' || (node.querySelector && node.querySelector('#game-stream'))) {
                        console.log('[Better xCloud] Stream container detected');
                        if (window.AndroidInterface) {
                            window.AndroidInterface.onStreamingStarted();
                        }
                    }
                });
            });
        });

        observer.observe(document.body, {
            childList: true,
            subtree: true
        });
    }

    // Cleanup on unload
    window.addEventListener('beforeunload', function() {
        if (window.AndroidInterface) {
            window.AndroidInterface.onStreamingStopped();
        }
    });

    console.log('[Better xCloud] Initialization complete');
})();
