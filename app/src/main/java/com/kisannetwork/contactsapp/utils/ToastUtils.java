package com.kisannetwork.contactsapp.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
