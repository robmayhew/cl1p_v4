package com.robmayhew.cl1p.control.message;

import com.robmayhew.cl1p.open.objects.Page;
import com.robmayhew.cl1p.storage.Cl1pObjectDBO;

public class MessageOut
{
    private MessageOutType type;
    private String rejectReason;
    private Cl1pObjectDBO cl1pObj;
    private Page page;

    public static MessageOut invalidMessage(MessageInType type)
    {
        MessageOut messageOut = new MessageOut();
        messageOut.type = MessageOutType.REJECT;
        messageOut.rejectReason = "Invalid message " + type;
        return messageOut;
    }

    public static MessageOut privateCl1p(MessageInType type)
    {
        MessageOut messageOut = new MessageOut();
        messageOut.type = MessageOutType.PRIVATE_CL1P;
        messageOut.rejectReason = "private " + type;
        return messageOut;
    }

    public static MessageOut page(Cl1pObjectDBO objectDBO, Page page, MessageOutType type)
    {
        MessageOut messageOut = new MessageOut();
        messageOut.page = page;
        messageOut.cl1pObj = objectDBO;
        messageOut.type = type;
        return messageOut;
    }

    public static MessageOut error()
    {
        MessageOut messageOut = new MessageOut();
        messageOut.type = MessageOutType.ERROR;
        return messageOut;
    }

    public MessageOutType getType()
    {
        return type;
    }

    public String getRejectReason()
    {
        return rejectReason;
    }

    public Cl1pObjectDBO getCl1pObj()
    {
        return cl1pObj;
    }

    public Page getPage()
    {
        return page;
    }

    public static MessageOut outOfDate(Cl1pObjectDBO dbo, Page page)
    {
        MessageOut out = new MessageOut();
        out.type = MessageOutType.OUT_OF_SYNC;
        out.page = page;
        out.cl1pObj = dbo;
        return out;
    }
}
