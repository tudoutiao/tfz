package com.my.tfz.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.my.tfz.MainApplication
import com.my.tfz.bean.AppItemInfo
import com.my.tfz.bean.ConstactBean
import com.my.tfz.bean.MessageBean
import com.my.tfz.bean.SimpleBean
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.InputStream

open class DataUtil {
    fun getInfoList(): ArrayList<AppItemInfo>? {
        try {
            val inputStream = MainApplication.context.assets.open("appdata.json")
            val jsonObject = JSONObject(parseJsonFromInputStream(inputStream))
            if (jsonObject != null) {
                if (!jsonObject.isNull("data")) {
                    var districts = Gson().fromJson<ArrayList<AppItemInfo>>(
                        jsonObject.optString("data"),
                        object :
                            TypeToken<List<AppItemInfo>>() {
                        }.type
                    )
                    return districts
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getMessageList(): List<MessageBean>? {
        try {
            val inputStream = MainApplication.context.assets.open("message.json")
            val jsonObject = JSONObject(parseJsonFromInputStream(inputStream))
            if (jsonObject != null) {
                if (!jsonObject.isNull("data")) {
                    var districts = Gson().fromJson<List<MessageBean>>(
                        jsonObject.optString("data"),
                        object :
                            TypeToken<List<MessageBean>>() {
                        }.type
                    )
                    return districts
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getConstactList(): List<ConstactBean>? {
        try {
            val inputStream = MainApplication.context.assets.open("constact.json")
            val jsonObject = JSONObject(parseJsonFromInputStream(inputStream))
            if (jsonObject != null) {
                if (!jsonObject.isNull("data")) {
                    var districts = Gson().fromJson<List<ConstactBean>>(
                        jsonObject.optString("data"),
                        object :
                            TypeToken<List<ConstactBean>>() {
                        }.type
                    )
                    return districts
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getMineList(): List<SimpleBean>? {
        try {
            val inputStream = MainApplication.context.assets.open("minedata.json")
            val jsonObject = JSONObject(parseJsonFromInputStream(inputStream))
            if (jsonObject != null) {
                if (!jsonObject.isNull("data")) {
                    var districts = Gson().fromJson<List<SimpleBean>>(
                        jsonObject.optString("data"),
                        object :
                            TypeToken<List<SimpleBean>>() {
                        }.type
                    )
                    return districts
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun parseJsonFromInputStream(inputStream: InputStream): String? {
        try {
            val content = ByteArrayOutputStream()
            var readBytes = 0
            val sBuffer = ByteArray(512)
            do {
                readBytes = inputStream.read(sBuffer)
                if (readBytes != -1) {
                    content.write(sBuffer, 0, readBytes)
                } else {
                    break;
                }
            } while (true)
            inputStream.close()
            content.close()
            val jsonStr = String(content.toByteArray())
            return jsonStr
        } catch (e: Exception) {
            e.printStackTrace()

            return null
        }
    }


}