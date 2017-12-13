
package com.jio.assignment.search.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entities implements Parcelable
{

    @SerializedName("hashtags")
    @Expose
    private List<Object> hashtags = null;
    @SerializedName("symbols")
    @Expose
    private List<Object> symbols = null;
    @SerializedName("user_mentions")
    @Expose
    private List<UserMention> userMentions = null;
    @SerializedName("urls")
    @Expose
    private List<Url> urls = null;
    public final static Creator<Entities> CREATOR = new Creator<Entities>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Entities createFromParcel(Parcel in) {
            return new Entities(in);
        }

        public Entities[] newArray(int size) {
            return (new Entities[size]);
        }

    }
    ;

    protected Entities(Parcel in) {
        in.readList(this.hashtags, (Object.class.getClassLoader()));
        in.readList(this.symbols, (Object.class.getClassLoader()));
        in.readList(this.userMentions, (com.jio.assignment.search.UserMention.class.getClassLoader()));
        in.readList(this.urls, (com.jio.assignment.search.Url.class.getClassLoader()));
    }

    public Entities() {
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

    public List<UserMention> getUserMentions() {
        return userMentions;
    }

    public void setUserMentions(List<UserMention> userMentions) {
        this.userMentions = userMentions;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
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
