package com.logitech.assignment.inventory.networkinteractor;

public interface LoginInteractor {

    void login(String username, String password, OnLoginFinishedListener
            listener);

    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

}
