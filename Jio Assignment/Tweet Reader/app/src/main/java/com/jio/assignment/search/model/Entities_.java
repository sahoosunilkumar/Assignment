
package com.jio.assignment.search.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entities_ implements Parcelable
{

    @SerializedName("url")
    @Expose
    private Url_ url;
    @SerializedName("description")
    @Expose
    private Description description;
    public final static Creator<Entities_> CREATOR = new Creator<Entities_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Entities_ createFromParcel(Parcel in) {
            return new Entities_(in);
        }

        public Entities_[] newArray(int size) {
            return (new Entities_[size]);
        }

    }
    ;

    protected Entities_(Parcel in) {
        this.url = ((Url_) in.readValue((Url_.class.getClassLoader())));
        this.description = ((Description) in.readValue((Description.class.getClassLoader())));
    }

    public Entities_() {
    }

    public Url_ getUrl() {
        return url;
    }

    public void setUrl(Url_ url) {
        this.url = url;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);
        dest.writeValue(description);
    }

    public int describeContents() {
        return  0;
    }

}
