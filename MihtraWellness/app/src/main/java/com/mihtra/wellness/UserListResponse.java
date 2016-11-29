
package com.mihtra.wellness;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserListResponse {

    @SerializedName("total_pages")
    private long totalPages;
    @SerializedName("current_page")
    @Expose
    private long currentPage;
    @SerializedName("result")
    @Expose
    private long result;
    @SerializedName("users")
    @Expose
    private List<User> users = new ArrayList<User>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserListResponse() {
    }

    /**
     * 
     * @param result
     * @param users
     * @param currentPage
     * @param totalPages
     */
    public UserListResponse(long totalPages, long currentPage, long result, List<User> users) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.result = result;
        this.users = users;
    }

    /**
     * 
     * @return
     *     The totalPages
     */
    public long getTotalPages() {
        return totalPages;
    }

    /**
     * 
     * @param totalPages
     *     The total_pages
     */
    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 
     * @return
     *     The currentPage
     */
    public long getCurrentPage() {
        return currentPage;
    }

    /**
     * 
     * @param currentPage
     *     The current_page
     */
    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 
     * @return
     *     The result
     */
    public long getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(long result) {
        this.result = result;
    }

    /**
     * 
     * @return
     *     The users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * 
     * @param users
     *     The users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
