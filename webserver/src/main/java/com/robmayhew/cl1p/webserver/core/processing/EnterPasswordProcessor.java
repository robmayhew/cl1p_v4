package com.robmayhew.cl1p.webserver.core.processing;

import com.robmayhew.cl1p.Cl1p;
import com.robmayhew.cl1p.open.objects.Page;
import com.robmayhew.cl1p.storage.Cl1pObjectDBO;
import com.robmayhew.cl1p.util.Cl1pStringUtil;
import com.robmayhew.cl1p.web.Cl1pRequest;
import com.robmayhew.cl1p.web.Cl1pResponse;
import com.robmayhew.cl1p.web.JSP;

public class EnterPasswordProcessor implements ActionProcessor {

    @Override
    public Cl1pResponse process(Cl1pRequest req) {
        MessageIn in = MessageIn.buildIn(MessageInType.VIEW_URL, req, req.getPageIn());
        MessageOut out = Cl1p.getMessenger().process(in);
        Cl1pObjectDBO dbo = out.getCl1pObj();
        if (dbo == null)
            return null; // Nothing to do
        if (Cl1pStringUtil.isNotSet(dbo.getPassword()))
            return null; // Nothing to do
        String passwordIn = req.getPassword();
        if (Cl1pStringUtil.isNotSet(passwordIn))
            return null; // No password
        if (passwordIn.equals(dbo.getPassword()))
            req.getSession().passwordEntered(dbo.getId().getId());

        Cl1pResponse response = new Cl1pResponse();
        Page page = out.getPage();
        Cl1p.getMarkdownConverter().convertToMarkdown(page);
        response.setPage(page);
        response.setJsp(JSP.VIEW_READ_ONLY);
        return response;
    }
}
