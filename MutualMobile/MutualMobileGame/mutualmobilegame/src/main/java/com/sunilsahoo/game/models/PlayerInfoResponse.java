package com.sunilsahoo.game.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerInfoResponse {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("balance")
    @Expose
    private long balance;
    @SerializedName("avatarLink")
    @Expose
    private String avatarLink;
    @SerializedName("lastLogindate")
    @Expose
    private String lastLogindate;

    /**
     * No args constructor for use in serialization
     */
    public PlayerInfoResponse() {
    }

    /**
     * @param balance
     * @param lastLogindate
     * @param name
     * @param avatarLink
     */
    public PlayerInfoResponse(String name, long balance, String avatarLink,
                              String lastLogindate) {
        super();
        this.name = name;
        this.balance = balance;
        this.avatarLink = avatarLink;
        this.lastLogindate = lastLogindate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public String getLastLogindate() {
        return lastLogindate;
    }

    public void setLastLogindate(String lastLogindate) {
        this.lastLogindate = lastLogindate;
    }

}
