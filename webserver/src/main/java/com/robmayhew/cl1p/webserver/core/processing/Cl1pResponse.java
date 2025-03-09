package com.robmayhew.cl1p.webserver.core.processing;

import com.robmayhew.cl1p.open.objects.Page;

public class Cl1pResponse
{
    private JSP jsp;
    private Page page;

    public JSP getJsp()
    {
        return jsp;
    }

    public void setJsp(JSP jsp)
    {
        this.jsp = jsp;
    }

    public Page getPage()
    {
        return page;
    }

    public void setPage(Page page)
    {
        this.page = page;
    }
}
