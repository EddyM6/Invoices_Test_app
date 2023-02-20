package com.xmm_homework.data.config

import android.content.SharedPreferences
import androidx.core.content.edit
import com.xmm_homework.domain.service.ConfigDataService

internal class DefaultConfigDataService(private val sharedPreferences: SharedPreferences) :
    ConfigDataService {
    override fun save(key: String, value: Int) {
        sharedPreferences.edit {
            putInt(key, value)
        }
    }

    override fun getInt(key: String, defaultValue: Int): Int =
        sharedPreferences.getInt(key, defaultValue)
}