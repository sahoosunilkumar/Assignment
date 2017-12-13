
package com.jio.assignment.search.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url_ implements Parcelable
{

    @SerializedName("urls")
    @Expose
    private List<Url__> urls = null;
    public final static Creator<Url_> CREATOR = new Creator<Url_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Url_ createFromParcel(Parcel in) {
            return new Url_(in);
        }

        public Url_[] newArray(int size) {
            return (new Url_[size]);
        }

    }
    ;

    protected Url_(Parcel in) {
        in.readList(this.urls, (com.jio.assignment.search.Url__.class.getClassLoader()));
    }

    public Url_() {
    }

    public List<Url__> getUrls() {
        return urls;
    }

    public void setUrls(List<Url__> urls) {
        this.urls = urls;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(urls);
    }

    public int describeContents() {
        return  0;
    }

}
