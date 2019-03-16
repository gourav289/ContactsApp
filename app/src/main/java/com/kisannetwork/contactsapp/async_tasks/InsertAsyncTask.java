package com.kisannetwork.contactsapp.async_tasks;

import android.os.AsyncTask;
import com.kisannetwork.contactsapp.room.SentMessagesDao;
import com.kisannetwork.contactsapp.room.SentMessagesModel;

public class InsertAsyncTask extends AsyncTask<SentMessagesModel,Void,Void> {
SentMessagesDao sentMessagesDao;
    public InsertAsyncTask(SentMessagesDao sentMessagesDao) {
        this.sentMessagesDao = sentMessagesDao;
    }

    @Override
    protected Void doInBackground(SentMessagesModel... questions) {
        sentMessagesDao.insert(questions[0]);
        return null;
    }
}
