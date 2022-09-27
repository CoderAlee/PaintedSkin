package org.alee.demo.skin.basic.ability.util

import com.blankj.utilcode.util.Utils
import com.tencent.mmkv.MMKV

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 *
 */
object MMKVUtil {
    private val mExecutor: MMKV?

    init {
        MMKV.initialize(Utils.getApp())
        mExecutor = MMKV.defaultMMKV()
    }

    fun putString(key: String, value: String?) {
        mExecutor?.run {
            encode(key, value)
            commit()
        }
    }

    fun putInt(key: String, value: Int) {
        mExecutor?.run {
            encode(key, value)
            commit()
        }
    }

    fun putBool(key: String, value: Boolean) {
        mExecutor?.run {
            encode(key, value)
            commit()
        }
    }

    fun putLong(key: String, value: Long) {
        mExecutor?.run {
            encode(key, value)
            commit()
        }
    }

    fun putFloat(key: String, value: Float) {
        mExecutor?.run {
            encode(key, value)
            commit()
        }
    }

    fun putByteArray(key: String, value: ByteArray?) {
        mExecutor?.run {
            encode(key, value)
            commit()
        }
    }

    fun getInt(key: String, defaultValue: Int = -1): Int {
        return mExecutor?.run {
            decodeInt(key, defaultValue)
        } ?: defaultValue
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return mExecutor?.run { decodeString(key, defaultValue) } ?: defaultValue
    }


    fun getBool(key: String, defaultValue: Boolean = false): Boolean {
        return mExecutor?.run { decodeBool(key, defaultValue) } ?: defaultValue
    }


    fun getFloat(key: String, defaultValue: Float = -1F): Float {
        return mExecutor?.run { decodeFloat(key, defaultValue) } ?: defaultValue
    }


    fun getLong(key: String, defaultValue: Long = -1L): Long {
        return mExecutor?.run { decodeLong(key, defaultValue) } ?: defaultValue
    }

    fun getByteArray(key: String, defaultValue: ByteArray? = null): ByteArray? {
        return mExecutor?.run { decodeBytes(key, defaultValue) } ?: defaultValue
    }
}

fun String.loadInt(defaultValue: Int = -1): Int {
    return MMKVUtil.getInt(this, defaultValue)
}

fun String.saveInt(value: Int) {
    MMKVUtil.putInt(this, value)
}

fun String.loadString(defaultValue: String = ""): String {
    return MMKVUtil.getString(this, defaultValue)
}

fun String.saveString(value: String?) {
    MMKVUtil.putString(this, value)
}

fun String.loadBoolean(defaultValue: Boolean = false): Boolean {
    return MMKVUtil.getBool(this, defaultValue)
}

fun String.saveBoolean(value: Boolean) {
    MMKVUtil.putBool(this, value)
}

fun String.loadFloat(defaultValue: Float = -1F): Float {
    return MMKVUtil.getFloat(this, defaultValue)
}

fun String.saveFloat(value: Float) {
    MMKVUtil.putFloat(this, value)
}

fun String.loadLong(defaultValue: Long = -1L): Long {
    return MMKVUtil.getLong(this, defaultValue)
}

fun String.saveLong(value: Long) {
    MMKVUtil.putLong(this, value)
}

fun String.loadByteArray(defaultValue: ByteArray? = null): ByteArray? {
    return MMKVUtil.getByteArray(this, defaultValue)
}

fun String.saveByteArray(value: ByteArray?) {
    MMKVUtil.putByteArray(this, value)
}