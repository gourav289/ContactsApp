package com.kisannetwork.contactsapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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

import java.io.IOException;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ComposeActivity extends BaseActivity {

    @BindView(R.id.ed_message)
    EditText edMessage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private  String otp="";
    private ContactsModel mContactsModel;
    String message="";

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
        message=edMessage.getText().toString().trim();
        if(!TextUtils.isEmpty(message)) {
            showProgress();
            final long currentTime = System.currentTimeMillis();
            SimpleDateFormat sf = new SimpleDateFormat("hh:mm a");
            final String time = sf.format(currentTime);
            try {
                post(Constants.SMS_URL, new  Callback(){

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("Failure");
                            }
                        });
                        hideProgress();
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        hideProgress();
                        SentMessagesModel sentMessagesModel = new SentMessagesModel(mContactsModel.getFirstName(), mContactsModel.getLastName(), otp, message, currentTime, time);
                        sentMessagesViewModel.insert(sentMessagesModel);
                        finish();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("SMS Sent");
                            }
                        });
                    }
                });
            } catch (IOException e) {
                hideProgress();
                e.printStackTrace();
            }
        }else{
            showToast(getString(R.string.enter_message));
        }
    }

    //Call to SMS API
    Call post(String url, Callback callback) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("To", mContactsModel.getPhoneNumber())
                .add("Body", message)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        OkHttpClient mClient = new OkHttpClient();
        Call response = mClient.newCall(request);
        response.enqueue(callback);
        return response;
    }
}
