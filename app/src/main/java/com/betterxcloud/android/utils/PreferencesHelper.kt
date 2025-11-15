package com.betterxcloud.android.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

/**
 * PreferencesHelper - Manages application preferences and settings.
 *
 * Provides a convenient abstraction for SharedPreferences operations
 * with type-safe methods for storing and retrieving user settings.
 *
 * Issue #27: Settings management for user preferences
 */
class PreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    /**
     * Saves a String value
     */
    fun putString(key: String, value: String) {
        try {
            sharedPreferences.edit().putString(key, value).apply()
            Log.d(TAG, "Saved string preference: $key")
        } catch (e: Exception) {
            Log.e(TAG, "Error saving string preference: $key", e)
        }
    }

    /**
     * Retrieves a String value
     */
    fun getString(key: String, defaultValue: String = ""): String {
        return try {
            sharedPreferences.getString(key, defaultValue) ?: defaultValue
        } catch (e: Exception) {
            Log.e(TAG, "Error retrieving string preference: $key", e)
            defaultValue
        }
    }

    /**
     * Saves a Boolean value
     */
    fun putBoolean(key: String, value: Boolean) {
        try {
            sharedPreferences.edit().putBoolean(key, value).apply()
            Log.d(TAG, "Saved boolean preference: $key = $value")
        } catch (e: Exception) {
            Log.e(TAG, "Error saving boolean preference: $key", e)
        }
    }

    /**
     * Retrieves a Boolean value
     */
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return try {
            sharedPreferences.getBoolean(key, defaultValue)
        } catch (e: Exception) {
            Log.e(TAG, "Error retrieving boolean preference: $key", e)
            defaultValue
        }
    }

    /**
     * Saves an Integer value
     */
    fun putInt(key: String, value: Int) {
        try {
            sharedPreferences.edit().putInt(key, value).apply()
            Log.d(TAG, "Saved int preference: $key = $value")
        } catch (e: Exception) {
            Log.e(TAG, "Error saving int preference: $key", e)
        }
    }

    /**
     * Retrieves an Integer value
     */
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return try {
            sharedPreferences.getInt(key, defaultValue)
        } catch (e: Exception) {
            Log.e(TAG, "Error retrieving int preference: $key", e)
            defaultValue
        }
    }

    /**
     * Saves a Long value
     */
    fun putLong(key: String, value: Long) {
        try {
            sharedPreferences.edit().putLong(key, value).apply()
            Log.d(TAG, "Saved long preference: $key = $value")
        } catch (e: Exception) {
            Log.e(TAG, "Error saving long preference: $key", e)
        }
    }

    /**
     * Retrieves a Long value
     */
    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return try {
            sharedPreferences.getLong(key, defaultValue)
        } catch (e: Exception) {
            Log.e(TAG, "Error retrieving long preference: $key", e)
            defaultValue
        }
    }

    /**
     * Saves a Float value
     */
    fun putFloat(key: String, value: Float) {
        try {
            sharedPreferences.edit().putFloat(key, value).apply()
            Log.d(TAG, "Saved float preference: $key = $value")
        } catch (e: Exception) {
            Log.e(TAG, "Error saving float preference: $key", e)
        }
    }

    /**
     * Retrieves a Float value
     */
    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return try {
            sharedPreferences.getFloat(key, defaultValue)
        } catch (e: Exception) {
            Log.e(TAG, "Error retrieving float preference: $key", e)
            defaultValue
        }
    }

    /**
     * Saves a Set of Strings
     */
    fun putStringSet(key: String, value: Set<String>) {
        try {
            sharedPreferences.edit().putStringSet(key, value).apply()
            Log.d(TAG, "Saved string set preference: $key")
        } catch (e: Exception) {
            Log.e(TAG, "Error saving string set preference: $key", e)
        }
    }

    /**
     * Retrieves a Set of Strings
     */
    fun getStringSet(key: String, defaultValue: Set<String> = emptySet()): Set<String> {
        return try {
            sharedPreferences.getStringSet(key, defaultValue) ?: defaultValue
        } catch (e: Exception) {
            Log.e(TAG, "Error retrieving string set preference: $key", e)
            defaultValue
        }
    }

    /**
     * Checks if a preference exists
     */
    fun contains(key: String): Boolean {
        return try {
            sharedPreferences.contains(key)
        } catch (e: Exception) {
            Log.e(TAG, "Error checking preference: $key", e)
            false
        }
    }

    /**
     * Removes a preference
     */
    fun remove(key: String) {
        try {
            sharedPreferences.edit().remove(key).apply()
            Log.d(TAG, "Removed preference: $key")
        } catch (e: Exception) {
            Log.e(TAG, "Error removing preference: $key", e)
        }
    }

    /**
     * Clears all preferences
     */
    fun clearAll() {
        try {
            sharedPreferences.edit().clear().apply()
            Log.d(TAG, "Cleared all preferences")
        } catch (e: Exception) {
            Log.e(TAG, "Error clearing preferences", e)
        }
    }

    /**
     * Gets all preferences as a map
     */
    fun getAll(): Map<String, *> {
        return try {
            sharedPreferences.all ?: emptyMap()
        } catch (e: Exception) {
            Log.e(TAG, "Error retrieving all preferences", e)
            emptyMap()
        }
    }

    // Application-specific preference keys
    object Keys {
        const val XBOX_ACCOUNT_EMAIL = "xbox_account_email"
        const val XBOX_ACCOUNT_NAME = "xbox_account_name"
        const val REMEMBER_LOGIN = "remember_login"
        const val ENABLE_HAPTICS = "enable_haptics"
        const val ENABLE_VOICE_SEARCH = "enable_voice_search"
        const val VIDEO_QUALITY = "video_quality"
        const val RESOLUTION = "resolution"
        const val AUTO_PLAY = "auto_play"
        const val LAST_PLAYED_GAME = "last_played_game"
        const val RECENT_GAMES = "recent_games"
        const val ENABLE_STATS_OVERLAY = "enable_stats_overlay"
        const val THEME_MODE = "theme_mode"
        const val ENABLE_NOTIFICATIONS = "enable_notifications"
        const val APP_VERSION = "app_version"
        const val FIRST_LAUNCH = "first_launch"
    }

    companion object {
        private const val TAG = "PreferencesHelper"
        private const val PREFS_NAME = "com.betterxcloud.android.preferences"

        // For singleton pattern
        @Volatile
        private var instance: PreferencesHelper? = null

        fun getInstance(context: Context): PreferencesHelper {
            return instance ?: synchronized(this) {
                instance ?: PreferencesHelper(context.applicationContext).also { instance = it }
            }
        }
    }
}
