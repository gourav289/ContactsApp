package com.kisannetwork.contactsapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.kisannetwork.contactsapp.R;
import com.kisannetwork.contactsapp.base.BaseActivity;
import com.kisannetwork.contactsapp.models.ContactsModel;
import com.kisannetwork.contactsapp.utils.CommonMethods;
import com.kisannetwork.contactsapp.utils.Constants;

import butterknife.BindView;

public class ComposeActivity extends BaseActivity {

    @BindView(R.id.ed_message)
    EditText edMessage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ContactsModel mContactsModel;
    @Override
    public int setLayout() {
        return R.layout.activity_compose;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            mContactsModel=bundle.getParcelable(Constants.CONTACT_MODEL);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.compose_message));
        edMessage.setText(getString(R.string.otp_message)+" "+ CommonMethods.getRandomNumber());

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
}
