package com.atlassian.hipchat.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatDetails {

    @SerializedName("mentions")
    @Expose
    private List<String> mentions = null;
    @SerializedName("emoticons")
    @Expose
    private List<String> emoticons = null;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    public List<String> getEmoticons() {
        return emoticons;
    }

    public void setEmoticons(List<String> emoticons) {
        this.emoticons = emoticons;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
