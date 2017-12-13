
package com.jio.assignment.search.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entities__ implements Parcelable
{

    @SerializedName("hashtags")
    @Expose
    private List<Object> hashtags = null;
    @SerializedName("symbols")
    @Expose
    private List<Object> symbols = null;
    @SerializedName("user_mentions")
    @Expose
    private List<Object> userMentions = null;
    @SerializedName("urls")
    @Expose
    private List<Url___> urls = null;
    public final static Creator<Entities__> CREATOR = new Creator<Entities__>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Entities__ createFromParcel(Parcel in) {
            return new Entities__(in);
        }

        public Entities__[] newArray(int size) {
            return (new Entities__[size]);
        }

    }
    ;

    protected Entities__(Parcel in) {
        in.readList(this.hashtags, (Object.class.getClassLoader()));
        in.readList(this.symbols, (Object.class.getClassLoader()));
        in.readList(this.userMentions, (Object.class.getClassLoader()));
        in.readList(this.urls, (com.jio.assignment.search.Url___.class.getClassLoader()));
    }

    public Entities__() {
    }

    public List<Object> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Object> hashtags) {
        this.hashtags = hashtags;
    }

    public List<Object> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Object> symbols) {
        this.symbols = symbols;
    }

    public List<Object> getUserMentions() {
        return userMentions;
    }

    public void setUserMentions(List<Object> userMentions) {
        this.userMentions = userMentions;
    }

    public List<Url___> getUrls() {
        return urls;
    }

    public void setUrls(List<Url___> urls) {
        this.urls = urls;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(hashtags);
        dest.writeList(symbols);
        dest.writeList(userMentions);
        dest.writeList(urls);
    }

    public int describeContents() {
        return  0;
    }

}
