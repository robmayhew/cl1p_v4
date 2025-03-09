package com.robmayhew.cl1p.webserver.core.processing;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.HashSet;

public class Cl1pSession {


    private String last;
    private final HashSet<Long> enteredPassword = new HashSet<>();
    private String requestLoginFor;
    private String lastPurchase;
    private boolean loggedIn;
    private String username;
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public boolean hasEnteredPassword(long cl1pId) {
        return enteredPassword.contains(cl1pId);
    }

    public void passwordEntered(long cl1pId) {
        enteredPassword.add(cl1pId);
    }

    public void setRequestLoginFor(String requestLoginFor) {
        this.requestLoginFor = requestLoginFor;
    }

    public String getRequestLoginFor() {
        return requestLoginFor;
    }

    public void setLastPurchase(String lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    public String getLastPurchase() {
        return lastPurchase;
    }
}
