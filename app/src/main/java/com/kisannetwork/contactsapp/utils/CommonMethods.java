package com.kisannetwork.contactsapp.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class CommonMethods {

    public static String loadJSONFromAsset(Context mContext) {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("yourfilename.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
