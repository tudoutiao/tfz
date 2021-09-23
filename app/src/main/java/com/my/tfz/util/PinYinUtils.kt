package com.my.tfz.util

import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination
import java.lang.StringBuilder

class PinYinUtils {
    fun getPinyin(text: String): String? {
        val chars = text.toCharArray()
        val sb = StringBuilder()
        val hanyuPinyinOutputFormat = HanyuPinyinOutputFormat()

        //取消音调
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE)
        //大写
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE)
        for (ch in chars) {
            if (Character.isWhitespace(ch)) {
                //如果是空格
                continue
            }

            // ch  ascii 表
            if (ch.toInt() > 128 || ch.toInt() < -127) {
                try {
                    //数组 是有多音字
                    val array: Array<String> =
                        PinyinHelper.toHanyuPinyinStringArray(ch, hanyuPinyinOutputFormat)
                    sb.append(array[0])
                } catch (badHanyuPinyinOutputFormatCombination: BadHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace()
                }
            } else {
//                #$%^&*
            }
        }
        return sb.toString()
    }
}