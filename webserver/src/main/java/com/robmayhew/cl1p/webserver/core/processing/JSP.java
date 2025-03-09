package com.robmayhew.cl1p.webserver.core.processing;

public enum JSP {
    VIEW("/WEB-INF/cl1p_view.jsp"),
    VIEW_READ_ONLY("/WEB-INF/cl1p_view_read_only.jsp"),
    DELETED("/WEB-INF/cl1p_deleted.jsp"),
    CREATED("/WEB-INF/cl1p_created.jsp"),
    ERROR("/WEB-INF/error.jsp"),
    PRIVATE_CL1P("/WEB-INF/privateCl1p.jsp"),
    RESERVED("/WEB-INF/reserved.jsp"),
    PASSWORD_REQUIRED("/WEB-INF/password_required.jsp"),
    CREATE_ACCOUNT_FOR_USER("/WEB-INF/createAccountForAccess.jsp"),
    RESTRICTED_TO_USER("/WEB-INF/restrictedToUser.jsp"),
    USAGE_LIMIT("/WEB-INF/usageLimit.jsp");
    final String path;

    JSP(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
