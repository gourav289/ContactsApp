package com.kisannetwork.contactsapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kisannetwork.contactsapp.R;
import com.kisannetwork.contactsapp.room.SentMessagesModel;
import com.kisannetwork.contactsapp.utils.CircularTextView;
import com.kisannetwork.contactsapp.utils.CommonMethods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SentMessagesAdapter extends RecyclerView.Adapter<SentMessagesAdapter.SentMessagesViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;
    private List<SentMessagesModel> mList;

    public SentMessagesAdapter(Context mContext) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    public void notifyList(List<SentMessagesModel> mList){
        this.mList=mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SentMessagesAdapter.SentMessagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.list_item_sent_messages,viewGroup,false);
        SentMessagesAdapter.SentMessagesViewHolder sentMessagesViewHolder=new SentMessagesAdapter.SentMessagesViewHolder(view);
        return sentMessagesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SentMessagesAdapter.SentMessagesViewHolder sentMessagesViewHolder, int position) {
        SentMessagesModel mSentMessagesModel=mList.get(position);
        sentMessagesViewHolder.setData(mSentMessagesModel);
    }

    @Override
    public int getItemCount() {
        if(mList!=null)
            return mList.size();
        else
            return 0;
    }

    public class SentMessagesViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_short_name)
        CircularTextView txtShortName;
        @BindView(R.id.txt_otp)
        TextView txtOtp;
        @BindView(R.id.txt_time)
        TextView txtTime;
        public SentMessagesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(SentMessagesModel mSentMessagesModel){
            txtName.setText(mSentMessagesModel.getFirstName()+" "+mSentMessagesModel.getLastName());
            txtShortName.setText(CommonMethods.getShortName(mSentMessagesModel.getFirstName(),mSentMessagesModel.getLastName()));
            txtShortName.setSolidColor(CommonMethods.getRandomColor());
            txtTime.setText(mSentMessagesModel.getTime());
            txtOtp.setText(mContext.getString(R.string.otp)+" : "+mSentMessagesModel.getOtp());
        }



    }

}
