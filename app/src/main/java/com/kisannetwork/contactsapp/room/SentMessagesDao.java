package com.kisannetwork.contactsapp.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SentMessagesDao {

    @Insert
    void insert(SentMessagesModel message);

    @Query("SELECT * FROM sent_messages ORDER BY date_time DESC")
    LiveData<List<SentMessagesModel>> getSentMessages();

}
