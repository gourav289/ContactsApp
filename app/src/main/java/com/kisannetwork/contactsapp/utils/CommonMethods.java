package com.kisannetwork.contactsapp.utils;

import android.content.Context;
import android.graphics.Color;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kisannetwork.contactsapp.models.ContactsModel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommonMethods {

    public static int getRandomColor(){
        String colors[]={"#FF5733","#FF2323","#FFC623","#BCFF23","#3AFF23","#23FF9E","#23DEFF","#235CFF","#6923FF","#F823FF"};
        return Color.parseColor(colors[new Random().nextInt(colors.length)]);
    }

    public static String getRandomNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public static String getNameChars(String firstName,String lastName){
        String name="";
        if(firstName.length()>0)
            name+=firstName.charAt(0);
        if (lastName.length()>0)
            name+=lastName.charAt(0);
        return name.toUpperCase();
    }

    public static String loadJSONFromAsset(Context mContext) {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("contacts.json");
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

    public static List<ContactsModel> getContactsList(Context mContext){
            List<ContactsModel> contactList = null;
            Gson gson = new Gson();
            String json = loadJSONFromAsset(mContext);
            if (json != null) {
                Type type = new TypeToken<List<ContactsModel>>() {
                }.getType();
                contactList = gson.fromJson(json, type);
            } else {
                contactList = new ArrayList<>();
            }
            return contactList;
    }
}
