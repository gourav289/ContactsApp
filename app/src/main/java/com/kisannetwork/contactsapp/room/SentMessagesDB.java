package com.kisannetwork.contactsapp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.kisannetwork.contactsapp.models.ContactsModel;

@Database(entities = {SentMessagesModel.class},version = 1)
public abstract class SentMessagesDB extends RoomDatabase {

    public abstract SentMessagesDao sentMessagesDao();

    private static volatile SentMessagesDB sentMessagesDBInstance;

    static SentMessagesDB getInstance(Context mContext){
        if(sentMessagesDBInstance==null){
            synchronized ((SentMessagesDB.class)){
                if(sentMessagesDBInstance==null){
                    sentMessagesDBInstance= Room.databaseBuilder(mContext.getApplicationContext(),SentMessagesDB.class,"sent_message_db").build();
                }
            }
        }
        return sentMessagesDBInstance;
    }
}
