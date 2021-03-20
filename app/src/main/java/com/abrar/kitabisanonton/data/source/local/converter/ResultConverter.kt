package com.abrar.kitabisanonton.data.source.local.converter

import androidx.room.TypeConverter
import com.abrar.kitabisanonton.domain.model.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResultConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<Result>): String {
            val type = object : TypeToken<MutableList<Result>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<Result> {
            val type = object : TypeToken<MutableList<Result>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}