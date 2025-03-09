package com.robmayhew.cl1p.web;

import com.robmayhew.cl1p.Cl1p;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class Cl1pSession {

    private static final String SESSION_ATTRIBUTE = "Cl1pSessionAttribute";
    private String last;
    private final HashSet<Long> enteredPassword = new HashSet<>();
    private String requestLoginFor;
    private String lastPurchase;


    public static Cl1pSession session()
    {
        ServletRequestAttributes attr = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        return session(attr.getRequest());
    }

    public static Cl1pSession session(HttpServletRequest request)
    {
        if (PrimaryFilter.isApi(request)) {
            Cl1pSession dummy = new Cl1pSession();
            return dummy;
        }
        Cl1pSession session = (Cl1pSession) request.getSession().getAttribute(SESSION_ATTRIBUTE);
        if (session == null) {
            session = new Cl1pSession();
            request.getSession().setAttribute(SESSION_ATTRIBUTE, session);
        }
        return session;
    }

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
