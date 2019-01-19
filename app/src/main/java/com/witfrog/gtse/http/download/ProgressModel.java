package com.witfrog.gtse.http.download;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : ProgressModel
 * </pre>
 */
public class ProgressModel implements Parcelable {

    private static final Creator<ProgressModel> CREATOR = new Creator<ProgressModel>() {
        @Override
        public ProgressModel createFromParcel(Parcel parcel) {
            return new ProgressModel(parcel);
        }

        @Override
        public ProgressModel[] newArray(int i) {
            return new ProgressModel[i];
        }
    };
    private              long                   currentBytes;
    private              long                   contentLength;
    private              boolean                done    = false;

    ProgressModel(long currentBytes, long contentLength, boolean done) {
        this.currentBytes = currentBytes;
        this.contentLength = contentLength;
        this.done = done;
    }

    private ProgressModel(Parcel parcel) {
        currentBytes = parcel.readLong();
        contentLength = parcel.readLong();
        done = parcel.readByte() != 0;
    }

    public long getCurrentBytes() {
        return currentBytes;
    }

    public void setCurrentBytes(long currentBytes) {
        this.currentBytes = currentBytes;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(currentBytes);
        parcel.writeLong(contentLength);
        parcel.writeByte((byte) (done ? 1 : 0));
    }

}
