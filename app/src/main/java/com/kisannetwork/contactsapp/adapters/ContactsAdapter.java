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
import com.kisannetwork.contactsapp.models.ContactsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;
    private List<ContactsModel> mList;
    private ClickListener itemClickListener;

    public ContactsAdapter(Context mContext,List<ContactsModel> mList) {
        this.mContext = mContext;
        this.mList=mList;
        this.inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.list_item_contacts,viewGroup,false);
        ContactsViewHolder contactsViewHolder=new ContactsViewHolder(view);
        return contactsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder contactsViewHolder, int position) {
        ContactsModel mContactsModel=mList.get(position);
        contactsViewHolder.setData(mContactsModel);
        contactsViewHolder.setListener(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_short_name)
        TextView txtShortName;
        @BindView(R.id.lin_main)
        LinearLayout linMain;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(ContactsModel mContactsModel){
            txtName.setText(mContactsModel.getFirstName()+" "+mContactsModel.getLastName());
            txtShortName.setText(mContactsModel.getShortName());
            txtShortName.setBackgroundColor(mContactsModel.getColorCode());
        }

        public void setListener(final int position){
            linMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener!=null)
                        itemClickListener.onListItemClick(position);
                }
            });

        }

    }

    public void setOnItemClickListener(ClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    public interface ClickListener{
        void onListItemClick(int position);
    }
}
