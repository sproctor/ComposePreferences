package com.github.sproctor.composepreferences

public interface PreferenceHandler {
    public fun putString(key: String, value: String)
    public fun putBoolean(key: String, value: Boolean)
    public fun putFloat(key: String, value: Float)
    public fun putInt(key: String, value: Int)
    public fun putIntList(key: String, values: List<Int>)
    public suspend fun getString(key: String): String
    public suspend fun getBoolean(key: String): Boolean
    public suspend fun getFloat(key: String): Float
    public suspend fun getInt(key: String): Int
    public suspend fun getIntList(key: String): List<Int>
}
