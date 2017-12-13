
package com.jio.assignment.search.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description_ implements Parcelable
{

    @SerializedName("urls")
    @Expose
    private List<Url______> urls = null;
    public final static Creator<Description_> CREATOR = new Creator<Description_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Description_ createFromParcel(Parcel in) {
            return new Description_(in);
        }

        public Description_[] newArray(int size) {
            return (new Description_[size]);
        }

    }
    ;

    protected Description_(Parcel in) {
        in.readList(this.urls, (com.jio.assignment.search.Url______.class.getClassLoader()));
    }

    public Description_() {
    }

    public List<Url______> getUrls() {
        return urls;
    }

    public void setUrls(List<Url______> urls) {
        this.urls = urls;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(urls);
    }

    public int describeContents() {
        return  0;
    }

}
