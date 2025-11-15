package com.betterxcloud.android.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Logger - Centralized logging utility for the application.
 *
 * Provides structured logging with multiple log levels and optional
 * file logging for debugging. Optimized for Fire Stick 4K development.
 *
 * Issue #28: Centralized logging for debugging and monitoring
 */
class Logger {

    companion object {

        private const val TAG = "BetterXCloud"
        private const val MAX_TAG_LENGTH = 23
        private var enableFileLogging = false
        private var logLevel = LogLevel.DEBUG

        /**
         * Log levels enum
         */
        enum class LogLevel(val level: Int) {
            VERBOSE(Log.VERBOSE),
            DEBUG(Log.DEBUG),
            INFO(Log.INFO),
            WARN(Log.WARN),
            ERROR(Log.ERROR)
        }

        /**
         * Sets the minimum log level
         */
        fun setLogLevel(level: LogLevel) {
            logLevel = level
            info(TAG, "Log level set to: ${level.name}")
        }

        /**
         * Enables or disables file logging
         */
        fun setFileLoggingEnabled(enabled: Boolean) {
            enableFileLogging = enabled
            info(TAG, "File logging: ${if (enabled) "enabled" else "disabled"}")
        }

        /**
         * Logs a verbose message
         */
        fun verbose(tag: String, message: String) {
            if (logLevel.level <= LogLevel.VERBOSE.level) {
                Log.v(sanitizeTag(tag), message)
            }
        }

        /**
         * Logs a verbose message with exception
         */
        fun verbose(tag: String, message: String, throwable: Throwable) {
            if (logLevel.level <= LogLevel.VERBOSE.level) {
                Log.v(sanitizeTag(tag), message, throwable)
            }
        }

        /**
         * Logs a debug message
         */
        fun debug(tag: String, message: String) {
            if (logLevel.level <= LogLevel.DEBUG.level) {
                Log.d(sanitizeTag(tag), message)
                logToFile("DEBUG", tag, message)
            }
        }

        /**
         * Logs a debug message with exception
         */
        fun debug(tag: String, message: String, throwable: Throwable) {
            if (logLevel.level <= LogLevel.DEBUG.level) {
                Log.d(sanitizeTag(tag), message, throwable)
                logToFile("DEBUG", tag, "$message: ${throwable.message}")
            }
        }

        /**
         * Logs an info message
         */
        fun info(tag: String, message: String) {
            if (logLevel.level <= LogLevel.INFO.level) {
                Log.i(sanitizeTag(tag), message)
                logToFile("INFO", tag, message)
            }
        }

        /**
         * Logs an info message with exception
         */
        fun info(tag: String, message: String, throwable: Throwable) {
            if (logLevel.level <= LogLevel.INFO.level) {
                Log.i(sanitizeTag(tag), message, throwable)
                logToFile("INFO", tag, "$message: ${throwable.message}")
            }
        }

        /**
         * Logs a warning message
         */
        fun warn(tag: String, message: String) {
            if (logLevel.level <= LogLevel.WARN.level) {
                Log.w(sanitizeTag(tag), message)
                logToFile("WARN", tag, message)
            }
        }

        /**
         * Logs a warning message with exception
         */
        fun warn(tag: String, message: String, throwable: Throwable) {
            if (logLevel.level <= LogLevel.WARN.level) {
                Log.w(sanitizeTag(tag), message, throwable)
                logToFile("WARN", tag, "$message: ${throwable.message}")
            }
        }

        /**
         * Logs an error message
         */
        fun error(tag: String, message: String) {
            if (logLevel.level <= LogLevel.ERROR.level) {
                Log.e(sanitizeTag(tag), message)
                logToFile("ERROR", tag, message)
            }
        }

        /**
         * Logs an error message with exception
         */
        fun error(tag: String, message: String, throwable: Throwable) {
            if (logLevel.level <= LogLevel.ERROR.level) {
                Log.e(sanitizeTag(tag), message, throwable)
                logToFile("ERROR", tag, "$message: ${throwable.localizedMessage}")
            }
        }

        /**
         * Logs exception stack trace
         */
        fun exception(tag: String, throwable: Throwable) {
            if (logLevel.level <= LogLevel.ERROR.level) {
                Log.e(sanitizeTag(tag), "Exception occurred", throwable)
                val stackTrace = Log.getStackTraceString(throwable)
                logToFile("ERROR", tag, "Exception: $stackTrace")
            }
        }

        /**
         * Logs a method entry
         */
        fun methodEntry(tag: String, methodName: String) {
            debug(tag, ">> $methodName()")
        }

        /**
         * Logs a method exit
         */
        fun methodExit(tag: String, methodName: String) {
            debug(tag, "<< $methodName()")
        }

        /**
         * Logs a method with return value
         */
        fun methodExit(tag: String, methodName: String, result: Any?) {
            debug(tag, "<< $methodName() = $result")
        }

        /**
         * Logs performance timing
         */
        fun logTiming(tag: String, operationName: String, durationMs: Long) {
            val level = when {
                durationMs < 100 -> "FAST"
                durationMs < 500 -> "NORMAL"
                durationMs < 1000 -> "SLOW"
                else -> "VERY_SLOW"
            }
            info(tag, "$operationName completed in ${durationMs}ms ($level)")
        }

        /**
         * Logs memory information
         */
        fun logMemoryInfo(tag: String) {
            val runtime = Runtime.getRuntime()
            val maxMemory = runtime.maxMemory() / 1048576L
            val totalMemory = runtime.totalMemory() / 1048576L
            val freeMemory = runtime.freeMemory() / 1048576L
            val usedMemory = totalMemory - freeMemory

            info(
                tag,
                "Memory - Used: ${usedMemory}MB / ${totalMemory}MB, " +
                        "Free: ${freeMemory}MB, Max: ${maxMemory}MB"
            )
        }

        /**
         * Creates a formatted log message with timestamp
         */
        private fun formatLogMessage(level: String, tag: String, message: String): String {
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).format(Date())
            return "[$timestamp] $level/$tag: $message"
        }

        /**
         * Sanitizes tag to ensure it's not too long
         */
        private fun sanitizeTag(tag: String): String {
            return if (tag.length > MAX_TAG_LENGTH) {
                tag.substring(0, MAX_TAG_LENGTH)
            } else {
                tag
            }
        }

        /**
         * Logs to file (placeholder for future implementation)
         */
        private fun logToFile(level: String, tag: String, message: String) {
            if (!enableFileLogging) {
                return
            }

            try {
                val formattedMessage = formatLogMessage(level, tag, message)
                // TODO: Implement file logging when needed
                // This would typically write to a file for debugging
                // and remote crash reporting
            } catch (e: Exception) {
                Log.e(TAG, "Error logging to file", e)
            }
        }
    }
}

/**
 * Extension function for easier logging from any class
 */
inline val <reified T> T.logger: Logger
    get() = Logger

/**
 * Quick logging helper that uses the class name as tag
 */
fun <T> T.logDebug(message: String) where T : Any {
    Logger.debug(this::class.java.simpleName, message)
}

fun <T> T.logInfo(message: String) where T : Any {
    Logger.info(this::class.java.simpleName, message)
}

fun <T> T.logWarn(message: String) where T : Any {
    Logger.warn(this::class.java.simpleName, message)
}

fun <T> T.logError(message: String, throwable: Throwable? = null) where T : Any {
    if (throwable != null) {
        Logger.error(this::class.java.simpleName, message, throwable)
    } else {
        Logger.error(this::class.java.simpleName, message)
    }
}
