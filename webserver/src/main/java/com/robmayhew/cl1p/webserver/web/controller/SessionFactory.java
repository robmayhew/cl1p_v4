package com.robmayhew.cl1p.webserver.web.controller;

import com.robmayhew.cl1p.webserver.core.processing.Cl1pSession;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



public class SessionFactory {

    private static final String SESSION_ATTRIBUTE = "Cl1pSessionAttribute";

    public static Cl1pSession session()
    {
        ServletRequestAttributes attr = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        return session(attr.getRequest());
    }

    public static Cl1pSession session(HttpServletRequest request)
    {
        Cl1pSession session = (Cl1pSession) request.getSession().getAttribute(SESSION_ATTRIBUTE);
        if (session == null) {
            session = new Cl1pSession();
            request.getSession().setAttribute(SESSION_ATTRIBUTE, session);
        }
        return session;
    }
}
