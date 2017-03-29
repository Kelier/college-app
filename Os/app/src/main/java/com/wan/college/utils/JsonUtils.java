package com.wan.college.utils;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by 万文杰 on 2017/1/13.
 */

public class JsonUtils {

    public static StringBuilder getJson(Activity activity, String jsonName) {
        InputStreamReader inputStreamReader;
        StringBuilder stringBuilder=null;

        try {
            inputStreamReader = new InputStreamReader(activity.getAssets().open(jsonName), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line;
            stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();
            Log.i("TAG", stringBuilder.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
