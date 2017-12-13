
package com.jio.assignment.search.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url______ implements Parcelable
{

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("expanded_url")
    @Expose
    private String expandedUrl;
    @SerializedName("display_url")
    @Expose
    private String displayUrl;
    @SerializedName("indices")
    @Expose
    private List<Integer> indices = null;
    public final static Creator<Url______> CREATOR = new Creator<Url______>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Url______ createFromParcel(Parcel in) {
            return new Url______(in);
        }

        public Url______[] newArray(int size) {
            return (new Url______[size]);
        }

    }
    ;

    protected Url______(Parcel in) {
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.expandedUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.displayUrl = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.indices, (Integer.class.getClassLoader()));
    }

    public Url______() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public void setExpandedUrl(String expandedUrl) {
        this.expandedUrl = expandedUrl;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);
        dest.writeValue(expandedUrl);
        dest.writeValue(displayUrl);
        dest.writeList(indices);
    }

    public int describeContents() {
        return  0;
    }

}
