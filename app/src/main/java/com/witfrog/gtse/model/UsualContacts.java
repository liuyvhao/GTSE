package com.witfrog.gtse.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UsualContacts implements Parcelable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
    }

    public UsualContacts() {
    }

    protected UsualContacts(Parcel in) {
        this.id = in.readString();
    }

    public static final Parcelable.Creator<UsualContacts> CREATOR = new Parcelable.Creator<UsualContacts>() {
        @Override
        public UsualContacts createFromParcel(Parcel source) {
            return new UsualContacts(source);
        }

        @Override
        public UsualContacts[] newArray(int size) {
            return new UsualContacts[size];
        }
    };
}
