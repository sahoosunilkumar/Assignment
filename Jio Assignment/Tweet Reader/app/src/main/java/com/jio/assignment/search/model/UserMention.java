
package com.jio.assignment.search.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMention implements Parcelable
{

    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_str")
    @Expose
    private String idStr;
    @SerializedName("indices")
    @Expose
    private List<Integer> indices = null;
    public final static Creator<UserMention> CREATOR = new Creator<UserMention>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UserMention createFromParcel(Parcel in) {
            return new UserMention(in);
        }

        public UserMention[] newArray(int size) {
            return (new UserMention[size]);
        }

    }
    ;

    protected UserMention(Parcel in) {
        this.screenName = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idStr = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.indices, (Integer.class.getClassLoader()));
    }

    public UserMention() {
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(screenName);
        dest.writeValue(name);
        dest.writeValue(id);
        dest.writeValue(idStr);
        dest.writeList(indices);
    }

    public int describeContents() {
        return  0;
    }

}
