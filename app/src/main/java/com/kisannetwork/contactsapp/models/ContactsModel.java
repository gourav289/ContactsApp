package com.kisannetwork.contactsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.kisannetwork.contactsapp.utils.CommonMethods;

import java.util.Random;

public class ContactsModel implements Parcelable {

    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("phone_number")
    private String phoneNumber;

    private int colorCode;
    private String shortName;

    protected ContactsModel(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        phoneNumber = in.readString();
        colorCode = in.readInt();
        shortName = in.readString();
    }

    public static final Creator<ContactsModel> CREATOR = new Creator<ContactsModel>() {
        @Override
        public ContactsModel createFromParcel(Parcel in) {
            return new ContactsModel(in);
        }

        @Override
        public ContactsModel[] newArray(int size) {
            return new ContactsModel[size];
        }
    };

    public String getShortName() {
        return CommonMethods.getNameChars(getFirstName(),getLastName());
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getColorCode() {
        return CommonMethods.getRandomColor();
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(phoneNumber);
        dest.writeInt(colorCode);
        dest.writeString(shortName);
    }
}
