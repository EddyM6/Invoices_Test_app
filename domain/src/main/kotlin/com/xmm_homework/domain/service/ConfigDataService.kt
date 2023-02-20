package com.xmm_homework.domain.service

interface ConfigDataService {
    fun save(key: String, value: Int)

    fun getInt(key: String, defaultValue: Int = -1): Int
}
