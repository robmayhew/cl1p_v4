package com.robmayhew.cl1p.control.message;

import com.robmayhew.cl1p.web.Cl1pRequest;
import com.robmayhew.cl1p.web.Cl1pResponse;

public interface ActionProcessor {
    Cl1pResponse process(Cl1pRequest req);
}
