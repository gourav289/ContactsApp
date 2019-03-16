package com.kisannetwork.contactsapp.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.kisannetwork.contactsapp.async_tasks.InsertAsyncTask;

import java.util.List;

public class SentMessagesViewModel extends AndroidViewModel {

    private SentMessagesDB sentMessagesDB;
    private SentMessagesDao sentMessagesDao;

    public SentMessagesViewModel(@NonNull Application application) {
        super(application);
        sentMessagesDB=SentMessagesDB.getInstance(application);
        sentMessagesDao=sentMessagesDB.sentMessagesDao();
    }

    public void insert(SentMessagesModel sentMessage){
        new InsertAsyncTask(sentMessagesDao).execute(sentMessage);
    }

    public LiveData<List<SentMessagesModel>> getSentMessages(){
        return sentMessagesDao.getSentMessages();
    }
}
