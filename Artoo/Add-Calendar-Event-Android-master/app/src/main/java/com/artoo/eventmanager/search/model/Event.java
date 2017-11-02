package com.artoo.eventmanager.search.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sunilkumarsahoo on 10/28/17.
 */
public class Event implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
    private String title;
    private String description;
    private Location location;
    private String attendee;
    private long startTime;
    private long endTime;

    public Event() {

    }

    protected Event(Parcel in) {
        title = in.readString();
        description = in.readString();
        location = (Location) in.readValue(Location.class.getClassLoader());
        attendee = in.readString();
        startTime = in.readLong();
        endTime = in.readLong();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttendee() {
        return attendee;
    }

    public void setAttendee(String attendee) {
        this.attendee = attendee;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeValue(location);
        dest.writeString(attendee);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
