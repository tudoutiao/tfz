package com.my.tfz.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.my.tfz.MainApplication;
import com.my.tfz.bean.AppItemInfo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class DataUtil {
    public static List<AppItemInfo> getInfoList() {
        String reuslt = "";
        try {
            InputStream inputStream = MainApplication.context.getResources().getAssets().open("appdata.json");
            ByteArrayOutputStream content = new ByteArrayOutputStream();
            int readBytes = 0;
            byte[] sBuffer = new byte[512];
            while ((readBytes = inputStream.read(sBuffer)) != -1) {
                content.write(sBuffer, 0, readBytes);
            }
            inputStream.close();
            content.close();
            reuslt = new String(content.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(reuslt))
            return null;
        List<AppItemInfo> list = new Gson().fromJson(reuslt, new TypeToken<List<AppItemInfo>>() {
        }.getType());

        return list;
    }


}
