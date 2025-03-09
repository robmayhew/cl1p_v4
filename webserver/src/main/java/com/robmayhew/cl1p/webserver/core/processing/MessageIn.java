package com.robmayhew.cl1p.webserver.core.processing;

import com.robmayhew.cl1p.Cl1p;
import com.robmayhew.cl1p.open.objects.Page;
import com.robmayhew.cl1p.storage.UserObject;
import com.robmayhew.cl1p.web.Cl1pRequest;

public class MessageIn {
    private final MessageInType type;
    private final Path path;
    private final Page data;
    private final String remoteAddress;
    private final String userAgent;
    private final UserObject user;
    private final Cl1pSession session;
    private final Cl1pRequest request;

    public MessageIn(MessageInType type, Cl1pRequest request, UserObject user, Page data) {
        this.type = type;
        this.path = request.getPath();
        this.data = data;
        this.remoteAddress = request.getRemoteAddress();
        this.userAgent = request.getUserAgent();
        this.user = user;
        this.session = request.getSession();
        this.request = request;
    }

    public Cl1pSession getSession() {
        return session;
    }


    public Cl1pRequest getRequest() {
        return request;
    }

    public UserObject getUser() {
        return user;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public MessageInType getType()
    {
        return type;
    }

    public Path getPath() {
        return path;
    }

    public static MessageIn buildIn(MessageInType type, Cl1pRequest req, Page data) {
        UserObject userObject = null;
        Cl1pSession session = req.getSession();
        if (session.isLoggedIn()) {
            userObject = Cl1p.getUserStorage().loadUser(session.getUserId());
        }
        MessageIn in = new MessageIn(type, req, userObject, data);
        return in;
    }

    public Page getData() {
        return data;
    }
}
