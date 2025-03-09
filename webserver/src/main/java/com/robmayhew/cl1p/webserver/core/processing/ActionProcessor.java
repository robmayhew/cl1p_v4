package com.robmayhew.cl1p.webserver.core.processing;



public interface ActionProcessor {
    Cl1pResponse process(Cl1pRequest req);
}
