package com.robmayhew.cl1p.webserver.core.processing;

import com.robmayhew.cl1p.Cl1p;
import com.robmayhew.cl1p.open.objects.Page;
import com.robmayhew.cl1p.web.Cl1pRequest;
import com.robmayhew.cl1p.web.Cl1pResponse;
import com.robmayhew.cl1p.web.JSP;

public class DeleteActionProcessor implements ActionProcessor {

    @Override
    public Cl1pResponse process(Cl1pRequest req) {
        if (ActionCommon.userDoesNotOwnCl1p(req)) return ActionCommon.errorResponse();
        Cl1p.getCl1pStorage().delete(req.getPath());
        MessageIn in = MessageIn.buildIn(MessageInType.VIEW_URL, req, req.getPageIn());
        MessageOut out = Cl1p.getMessenger().process(in);
        Page pageOut = out.getPage();
        Cl1pResponse response = new Cl1pResponse();
        response.setPage(pageOut);
        response.setJsp(JSP.VIEW);
        return response;
    }
}
