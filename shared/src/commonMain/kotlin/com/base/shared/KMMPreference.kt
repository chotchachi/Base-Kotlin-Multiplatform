package com.base.shared

class KMMPreference(private val context: KMMContext) {
    fun put(key: String, value: Int) {
        context.putInt(key, value)
    }

    fun put(key: String, value: String?) {
        context.putString(key, value)
    }

    fun put(key: String, value: Boolean) {
        context.putBool(key, value)
    }

    fun getInt(key: String, default: Int): Int {
        return context.getInt(key, default)
    }

    fun getString(key: String): String? {
        return context.getString(key)
    }

    fun getBool(key: String, default: Boolean): Boolean {
        return context.getBool(key, default)
    }
}
