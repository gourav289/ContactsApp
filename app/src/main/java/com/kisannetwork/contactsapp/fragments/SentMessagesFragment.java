package com.kisannetwork.contactsapp.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kisannetwork.contactsapp.R;
import com.kisannetwork.contactsapp.adapters.SentMessagesAdapter;
import com.kisannetwork.contactsapp.base.BaseFragment;
import com.kisannetwork.contactsapp.room.SentMessagesModel;
import com.kisannetwork.contactsapp.room.SentMessagesViewModel;

import java.util.List;

import butterknife.BindView;

public class SentMessagesFragment extends BaseFragment {

    @BindView(R.id.rv_sent_messages)
    RecyclerView rvSentMessages;

    private SentMessagesViewModel sentMessagesViewModel;
    private SentMessagesAdapter messagesAdapter;

    @Override
    public View setLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sent_messages,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sentMessagesViewModel= ViewModelProviders.of(this).get(SentMessagesViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        messagesAdapter=new SentMessagesAdapter(getActivity());
        rvSentMessages.setAdapter(messagesAdapter);
        rvSentMessages.setLayoutManager(new LinearLayoutManager(getActivity()));

        sentMessagesViewModel.getSentMessages().observe(getActivity(), new Observer<List<SentMessagesModel>>() {
            @Override
            public void onChanged(@Nullable List<SentMessagesModel> sentMessagesModels) {
                messagesAdapter.notifyList(sentMessagesModels);
            }
        });
    }
}
