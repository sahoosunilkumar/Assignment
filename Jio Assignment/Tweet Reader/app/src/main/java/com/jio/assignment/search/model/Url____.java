
package com.jio.assignment.search.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url____ implements Parcelable
{

    @SerializedName("urls")
    @Expose
    private List<Url_____> urls = null;
    public final static Creator<Url____> CREATOR = new Creator<Url____>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Url____ createFromParcel(Parcel in) {
            return new Url____(in);
        }

        public Url____[] newArray(int size) {
            return (new Url____[size]);
        }

    }
    ;

    protected Url____(Parcel in) {
        in.readList(this.urls, (com.jio.assignment.search.Url_____.class.getClassLoader()));
    }

    public Url____() {
    }

    public List<Url_____> getUrls() {
        return urls;
    }

    public void setUrls(List<Url_____> urls) {
        this.urls = urls;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(urls);
    }

    public int describeContents() {
        return  0;
    }

}
