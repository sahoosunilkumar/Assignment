package com.mihtra.wellness;

/**
 * Created by MIHTRA WELLNESS PVT on 22-10-2016.
 */

public interface HttpConnectionListener {
    void onSuccess(UserListResponse userList);
    void onFailure(String reason);
}
