package com.robmayhew.cl1p.webserver.core.processing;

import com.robmayhew.cl1p.web.Cl1pRequest;
import com.robmayhew.cl1p.web.Cl1pResponse;

public enum Cl1pAction {

    EDIT(473001, new EditActionProcessor()),
    DELETE(473002, new DeleteActionProcessor()),
    ENTER_PASSWORD(473003, new EnterPasswordProcessor());


    private final int code;
    private final ActionProcessor processor;

    Cl1pAction(int code, ActionProcessor actionProcessor) {
        this.code = code;
        this.processor = actionProcessor;
    }

    public static Cl1pAction fromCode(String s) {
        int actionCode;
        try {
            actionCode = Integer.parseInt(s);
        } catch (NumberFormatException ignored) {
            return null;
        }
        for (Cl1pAction action : Cl1pAction.values()) {
            if (action.getCode() == actionCode)
                return action;
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public Cl1pResponse process(Cl1pRequest req) {
        if (processor == null)
            return null;
        return processor.process(req);
    }
}
