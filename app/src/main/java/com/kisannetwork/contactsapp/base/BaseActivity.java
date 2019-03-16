package com.kisannetwork.contactsapp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kisannetwork.contactsapp.utils.MyProgressDialog;
import com.kisannetwork.contactsapp.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder binder;
    public abstract int setLayout();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(setLayout()!=0)
            setContentView(setLayout());
        binder= ButterKnife.bind(this);
    }

    protected void showToast(String message){
        ToastUtils.showToast(this,message);
    }

    protected void showProgress(){
        MyProgressDialog.show(this);
    }

    protected void hideProgress(){
        MyProgressDialog.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binder!=null)
            binder.unbind();
    }
}
