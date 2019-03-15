package com.kisannetwork.contactsapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kisannetwork.contactsapp.adapters.ViewPagerAdapter;
import com.kisannetwork.contactsapp.base.BaseActivity;
import com.kisannetwork.contactsapp.fragments.ContactsFragment;
import com.kisannetwork.contactsapp.fragments.SentMessagesFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    private Toolbar toolbar;
    @BindView(R.id.tabs)
    private TabLayout tabLayout;
    @BindView(R.id.viewpager)
    private ViewPager viewPager;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ContactsFragment(), "Contacts");
        adapter.addFragment(new SentMessagesFragment(), "Sent Messages");
        viewPager.setAdapter(adapter);
    }

}