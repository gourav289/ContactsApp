package com.kisannetwork.contactsapp.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "sent_messages")
public class SentMessagesModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="first_name")
    private String firstName;

    @ColumnInfo(name="last_name")
    private String lastName;

    private String otp;

    private String message;

    @ColumnInfo(name = "date_time")
    private long dateTime;

    private String time;


    public SentMessagesModel(String firstName, String lastName, String otp, String message, long dateTime, String time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.otp = otp;
        this.message = message;
        this.dateTime = dateTime;
        this.time=time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
