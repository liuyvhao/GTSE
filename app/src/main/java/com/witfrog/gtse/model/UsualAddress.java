package com.witfrog.gtse.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UsualAddress implements Parcelable {

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

    public UsualAddress() {
    }

    protected UsualAddress(Parcel in) {
        this.id = in.readString();
    }

    public static final Parcelable.Creator<UsualAddress> CREATOR = new Parcelable.Creator<UsualAddress>() {
        @Override
        public UsualAddress createFromParcel(Parcel source) {
            return new UsualAddress(source);
        }

        @Override
        public UsualAddress[] newArray(int size) {
            return new UsualAddress[size];
        }
    };
}
