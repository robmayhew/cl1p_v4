package com.robmayhew.cl1p.webserver.core.processing;


public enum MessageInType {

    VIEW_URL(100),
    SUBMIT_PLAIN_DATA(101);

    final int id;

    MessageInType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
