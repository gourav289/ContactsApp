package com.kisannetwork.contactsapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kisannetwork.contactsapp.R;
import com.kisannetwork.contactsapp.activities.ContactDetailsActivity;
import com.kisannetwork.contactsapp.adapters.ContactsAdapter;
import com.kisannetwork.contactsapp.base.BaseFragment;
import com.kisannetwork.contactsapp.models.ContactsModel;
import com.kisannetwork.contactsapp.utils.CommonMethods;
import com.kisannetwork.contactsapp.utils.Constants;

import java.util.List;

import butterknife.BindView;

public class ContactsFragment extends BaseFragment {

    @BindView(R.id.rv_contacts)
    RecyclerView rvContacts;

    private List<ContactsModel> mList;
    private ContactsAdapter mContactsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList= CommonMethods.getContactsList(getActivity());
    }

    @Override
    public View setLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContactsAdapter=new ContactsAdapter(getActivity(),mList);
        rvContacts.setAdapter(mContactsAdapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));

        mContactsAdapter.setOnItemClickListener(new ContactsAdapter.ClickListener() {
            @Override
            public void onListItemClick(int position) {
                Intent mIntent=new Intent(getActivity(), ContactDetailsActivity.class);
                mIntent.putExtra(Constants.CONTACT_MODEL,mList.get(position));
                startActivity(mIntent);
            }
        });
    }
}
