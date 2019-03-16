package com.kisannetwork.contactsapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.kisannetwork.contactsapp.R;
import com.kisannetwork.contactsapp.base.BaseActivity;
import com.kisannetwork.contactsapp.models.ContactsModel;
import com.kisannetwork.contactsapp.room.SentMessagesModel;
import com.kisannetwork.contactsapp.room.SentMessagesViewModel;
import com.kisannetwork.contactsapp.utils.CommonMethods;
import com.kisannetwork.contactsapp.utils.Constants;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.OnClick;

public class ComposeActivity extends BaseActivity {

    @BindView(R.id.ed_message)
    EditText edMessage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private  String otp="";
    private ContactsModel mContactsModel;

    private SentMessagesViewModel sentMessagesViewModel;

    @Override
    public int setLayout() {
        return R.layout.activity_compose;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sentMessagesViewModel= ViewModelProviders.of(this).get(SentMessagesViewModel.class);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            mContactsModel=bundle.getParcelable(Constants.CONTACT_MODEL);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.compose_message));
        toolbar.setTitleTextColor(Color.WHITE);

        otp=CommonMethods.getRandomNumber();
        edMessage.setText(getString(R.string.otp_message)+" "+ otp);
        edMessage.setSelection(edMessage.getText().length());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_send)
    void onSendClick(){
        long currentTime= System.currentTimeMillis();
        SimpleDateFormat sf=new SimpleDateFormat("hh:mm a");
        String time=sf.format(currentTime);
        SentMessagesModel sentMessagesModel=new SentMessagesModel(mContactsModel.getFirstName(),mContactsModel.getLastName(),otp,edMessage.getText().toString().trim(),currentTime,time);
        sentMessagesViewModel.insert(sentMessagesModel);
    }
}
