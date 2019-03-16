package com.kisannetwork.contactsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kisannetwork.contactsapp.R;
import com.kisannetwork.contactsapp.base.BaseActivity;
import com.kisannetwork.contactsapp.models.ContactsModel;
import com.kisannetwork.contactsapp.utils.CircularTextView;
import com.kisannetwork.contactsapp.utils.CommonMethods;
import com.kisannetwork.contactsapp.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class ContactDetailsActivity extends BaseActivity {

    @BindView(R.id.lin_top)
    FrameLayout linTop;
    @BindView(R.id.txt_short_name)
    CircularTextView txtShortName;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_phone)
    TextView txtPhone;

    private ContactsModel mContactsModel;

    @Override
    public int setLayout() {
        return R.layout.activity_contact_details;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null)
            mContactsModel=bundle.getParcelable(Constants.CONTACT_MODEL);
        if(mContactsModel!=null) {
            setData(mContactsModel);
        }
    }

    private void setData(ContactsModel mContactsModel){
        int bgColor=CommonMethods.getRandomColor();
        txtShortName.setText(CommonMethods.getShortName(mContactsModel.getFirstName(),mContactsModel.getLastName()));
        txtShortName.setSolidColor(bgColor);
        linTop.setBackgroundColor(bgColor);
        txtName.setText(mContactsModel.getFirstName()+" "+mContactsModel.getLastName());
        txtPhone.setText(mContactsModel.getPhoneNumber());
    }

    @OnClick(R.id.btn_send)
    void onSendClick(){
        Intent mIntent=new Intent(this,ComposeActivity.class);
        mIntent.putExtra(Constants.CONTACT_MODEL,mContactsModel);
        startActivity(mIntent);
    }

    @OnClick(R.id.ibtn_back)
    void onBack(){
        finish();
    }
}
