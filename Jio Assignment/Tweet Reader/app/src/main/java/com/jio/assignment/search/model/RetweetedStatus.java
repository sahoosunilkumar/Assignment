
package com.jio.assignment.search.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetweetedStatus implements Parcelable
{

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_str")
    @Expose
    private String idStr;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("truncated")
    @Expose
    private Boolean truncated;
    @SerializedName("entities")
    @Expose
    private Entities__ entities;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("in_reply_to_status_id")
    @Expose
    private Object inReplyToStatusId;
    @SerializedName("in_reply_to_status_id_str")
    @Expose
    private Object inReplyToStatusIdStr;
    @SerializedName("in_reply_to_user_id")
    @Expose
    private Object inReplyToUserId;
    @SerializedName("in_reply_to_user_id_str")
    @Expose
    private Object inReplyToUserIdStr;
    @SerializedName("in_reply_to_screen_name")
    @Expose
    private Object inReplyToScreenName;
    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("geo")
    @Expose
    private Object geo;
    @SerializedName("coordinates")
    @Expose
    private Object coordinates;
    @SerializedName("place")
    @Expose
    private Object place;
    @SerializedName("contributors")
    @Expose
    private Object contributors;
    @SerializedName("is_quote_status")
    @Expose
    private Boolean isQuoteStatus;
    @SerializedName("retweet_count")
    @Expose
    private Integer retweetCount;
    @SerializedName("favorite_count")
    @Expose
    private Integer favoriteCount;
    @SerializedName("favorited")
    @Expose
    private Boolean favorited;
    @SerializedName("retweeted")
    @Expose
    private Boolean retweeted;
    @SerializedName("possibly_sensitive")
    @Expose
    private Boolean possiblySensitive;
    @SerializedName("lang")
    @Expose
    private String lang;
    public final static Creator<RetweetedStatus> CREATOR = new Creator<RetweetedStatus>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RetweetedStatus createFromParcel(Parcel in) {
            return new RetweetedStatus(in);
        }

        public RetweetedStatus[] newArray(int size) {
            return (new RetweetedStatus[size]);
        }

    }
    ;

    protected RetweetedStatus(Parcel in) {
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idStr = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((String) in.readValue((String.class.getClassLoader())));
        this.truncated = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.entities = ((Entities__) in.readValue((Entities__.class.getClassLoader())));
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.inReplyToStatusId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.inReplyToStatusIdStr = ((Object) in.readValue((Object.class.getClassLoader())));
        this.inReplyToUserId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.inReplyToUserIdStr = ((Object) in.readValue((Object.class.getClassLoader())));
        this.inReplyToScreenName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.user = ((User_) in.readValue((User_.class.getClassLoader())));
        this.geo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.coordinates = ((Object) in.readValue((Object.class.getClassLoader())));
        this.place = ((Object) in.readValue((Object.class.getClassLoader())));
        this.contributors = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isQuoteStatus = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.retweetCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.favoriteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.favorited = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.retweeted = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.possiblySensitive = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.lang = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RetweetedStatus() {
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getTruncated() {
        return truncated;
    }

    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    public Entities__ getEntities() {
        return entities;
    }

    public void setEntities(Entities__ entities) {
        this.entities = entities;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Object getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setInReplyToStatusId(Object inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    public Object getInReplyToStatusIdStr() {
        return inReplyToStatusIdStr;
    }

    public void setInReplyToStatusIdStr(Object inReplyToStatusIdStr) {
        this.inReplyToStatusIdStr = inReplyToStatusIdStr;
    }

    public Object getInReplyToUserId() {
        return inReplyToUserId;
    }

    public void setInReplyToUserId(Object inReplyToUserId) {
        this.inReplyToUserId = inReplyToUserId;
    }

    public Object getInReplyToUserIdStr() {
        return inReplyToUserIdStr;
    }

    public void setInReplyToUserIdStr(Object inReplyToUserIdStr) {
        this.inReplyToUserIdStr = inReplyToUserIdStr;
    }

    public Object getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    public void setInReplyToScreenName(Object inReplyToScreenName) {
        this.inReplyToScreenName = inReplyToScreenName;
    }

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }

    public Object getGeo() {
        return geo;
    }

    public void setGeo(Object geo) {
        this.geo = geo;
    }

    public Object getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Object coordinates) {
        this.coordinates = coordinates;
    }

    public Object getPlace() {
        return place;
    }

    public void setPlace(Object place) {
        this.place = place;
    }

    public Object getContributors() {
        return contributors;
    }

    public void setContributors(Object contributors) {
        this.contributors = contributors;
    }

    public Boolean getIsQuoteStatus() {
        return isQuoteStatus;
    }

    public void setIsQuoteStatus(Boolean isQuoteStatus) {
        this.isQuoteStatus = isQuoteStatus;
    }

    public Integer getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(Integer retweetCount) {
        this.retweetCount = retweetCount;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Boolean getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }

    public Boolean getPossiblySensitive() {
        return possiblySensitive;
    }

    public void setPossiblySensitive(Boolean possiblySensitive) {
        this.possiblySensitive = possiblySensitive;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(createdAt);
        dest.writeValue(id);
        dest.writeValue(idStr);
        dest.writeValue(text);
        dest.writeValue(truncated);
        dest.writeValue(entities);
        dest.writeValue(source);
        dest.writeValue(inReplyToStatusId);
        dest.writeValue(inReplyToStatusIdStr);
        dest.writeValue(inReplyToUserId);
        dest.writeValue(inReplyToUserIdStr);
        dest.writeValue(inReplyToScreenName);
        dest.writeValue(user);
        dest.writeValue(geo);
        dest.writeValue(coordinates);
        dest.writeValue(place);
        dest.writeValue(contributors);
        dest.writeValue(isQuoteStatus);
        dest.writeValue(retweetCount);
        dest.writeValue(favoriteCount);
        dest.writeValue(favorited);
        dest.writeValue(retweeted);
        dest.writeValue(possiblySensitive);
        dest.writeValue(lang);
    }

    public int describeContents() {
        return  0;
    }

}
