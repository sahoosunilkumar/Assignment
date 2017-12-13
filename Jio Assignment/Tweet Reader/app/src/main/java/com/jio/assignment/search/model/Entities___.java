
package com.jio.assignment.search.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entities___ implements Parcelable
{

    @SerializedName("url")
    @Expose
    private Url____ url;
    @SerializedName("description")
    @Expose
    private Description_ description;
    public final static Creator<Entities___> CREATOR = new Creator<Entities___>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Entities___ createFromParcel(Parcel in) {
            return new Entities___(in);
        }

        public Entities___[] newArray(int size) {
            return (new Entities___[size]);
        }

    }
    ;

    protected Entities___(Parcel in) {
        this.url = ((Url____) in.readValue((Url____.class.getClassLoader())));
        this.description = ((Description_) in.readValue((Description_.class.getClassLoader())));
    }

    public Entities___() {
    }

    public Url____ getUrl() {
        return url;
    }

    public void setUrl(Url____ url) {
        this.url = url;
    }

    public Description_ getDescription() {
        return description;
    }

    public void setDescription(Description_ description) {
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
