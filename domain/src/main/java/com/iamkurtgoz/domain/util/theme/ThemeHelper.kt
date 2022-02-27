package com.iamkurtgoz.domain.util.theme

import android.app.Application
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit

class ThemeHelper(application: Application) {

    private val sharedPreferences by lazy { application.getSharedPreferences(THEME_TYPE_PREFERENCES, ContextWrapper.MODE_PRIVATE) }

    companion object {
        private const val THEME_TYPE = "themeType"
        private const val THEME_TYPE_PREFERENCES = "theme_type_preferences"
    }

    fun applyTheme() {
        val mode = when (sharedPreferences.getString(THEME_TYPE, ThemeType.LIGHT_MODE.type)) {
            ThemeType.LIGHT_MODE.type -> ThemeType.LIGHT_MODE
            ThemeType.DARK_MODE.type -> ThemeType.DARK_MODE
            else -> ThemeType.LIGHT_MODE
        }
        sharedPreferences.edit {
            putString(THEME_TYPE, mode.type)
        }
    }


    fun applyThemeExtension(theme: ThemeType) {
        val mode = when (theme) {
            ThemeType.LIGHT_MODE -> AppCompatDelegate.MODE_NIGHT_NO
            ThemeType.DARK_MODE -> AppCompatDelegate.MODE_NIGHT_YES
        }
        AppCompatDelegate.setDefaultNightMode(mode)
        sharedPreferences.edit {
            putString(THEME_TYPE, theme.type)
        }
    }

    fun getCurrentTheme(): ThemeType {
        return when (sharedPreferences.getString(THEME_TYPE, ThemeType.LIGHT_MODE.type)) {
            ThemeType.LIGHT_MODE.name -> ThemeType.LIGHT_MODE
            ThemeType.DARK_MODE.name -> ThemeType.DARK_MODE
            else -> ThemeType.LIGHT_MODE
        }
    }
}