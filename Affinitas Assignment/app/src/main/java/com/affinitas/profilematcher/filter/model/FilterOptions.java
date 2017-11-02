package com.affinitas.profilematcher.filter.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.affinitas.profilematcher.common.utils.Constants;

public class FilterOptions implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FilterOptions> CREATOR = new Parcelable.Creator<FilterOptions>() {
        @Override
        public FilterOptions createFromParcel(Parcel in) {
            return new FilterOptions(in);
        }

        @Override
        public FilterOptions[] newArray(int size) {
            return new FilterOptions[size];
        }
    };
    private int minCompatibilityScore = Constants.MIN_COMPATIBILITY;
    private int maxCompatibilityScore = Constants.MAX_COMPATIBILITY;
    private int maxDistance = Integer.MAX_VALUE;
    private int minHeight = Constants.MIN_HEIGHT;
    private int maxHeight = Constants.MAX_HEIGHT;
    private boolean hasContact;
    private boolean hasPhoto;
    private boolean isFavourite;

    private int minAge = Constants.MIN_AGE;
    private int maxAge = Constants.MAX_AGE;

    public FilterOptions() {

    }

    protected FilterOptions(Parcel in) {
        minCompatibilityScore = in.readInt();
        maxCompatibilityScore = in.readInt();
        maxDistance = in.readInt();
        minHeight = in.readInt();
        maxHeight = in.readInt();
        minAge = in.readInt();
        maxAge = in.readInt();
        hasContact = in.readByte() != 0x00;
        hasPhoto = in.readByte() != 0x00;
        isFavourite = in.readByte() != 0x00;
    }

    public int getMinCompatibilityScore() {
        return minCompatibilityScore;
    }

    public void setMinCompatibilityScore(int minCompatibilityScore) {
        this.minCompatibilityScore = minCompatibilityScore;
    }

    public int getMaxCompatibilityScore() {
        return maxCompatibilityScore;
    }

    public void setMaxCompatibilityScore(int maxCompatibilityScore) {
        this.maxCompatibilityScore = maxCompatibilityScore;
    }


    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public boolean isHasContact() {
        return hasContact;
    }

    public void setHasContact(boolean hasContact) {
        this.hasContact = hasContact;
    }

    public boolean isHasPhoto() {
        return hasPhoto;
    }

    public void setHasPhoto(boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(minCompatibilityScore);
        dest.writeInt(maxCompatibilityScore);
        dest.writeInt(maxDistance);
        dest.writeInt(minHeight);
        dest.writeInt(maxHeight);

        dest.writeInt(minAge);
        dest.writeInt(maxAge);
        dest.writeByte((byte) (hasContact ? 0x01 : 0x00));
        dest.writeByte((byte) (hasPhoto ? 0x01 : 0x00));
        dest.writeByte((byte) (isFavourite ? 0x01 : 0x00));
    }
}