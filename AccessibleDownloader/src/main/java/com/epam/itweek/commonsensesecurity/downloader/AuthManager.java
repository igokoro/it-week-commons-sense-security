package com.epam.itweek.commonsensesecurity.downloader;

public class AuthManager {

    public static AuthManager getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        public static final AuthManager instance = new AuthManager();
    }

    public String getToken() {
        return "secure_token";
    }
}
