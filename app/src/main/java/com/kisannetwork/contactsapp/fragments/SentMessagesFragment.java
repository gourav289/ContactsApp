package com.kisannetwork.contactsapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kisannetwork.contactsapp.R;
import com.kisannetwork.contactsapp.base.BaseFragment;

public class SentMessagesFragment extends BaseFragment {
    @Override
    public View setLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sent_messages,container,false);
    }
}
