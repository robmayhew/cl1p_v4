package com.robmayhew.cl1p.open.objects;

import com.robmayhew.cl1p.area.bouncer.BouncerStatus;
import com.robmayhew.cl1p.area.ownership.wrapper.QuickOwned;
import com.robmayhew.cl1p.storage.AnnonDeleteToken;

public class Page {

    private String path;
    private boolean blank;
    private String content = "";
    private String objId;
    private String objSeq;
    private int cl1pType;
    private long goodTillTime;
    private boolean allowViewAfterGtt;
    private boolean accessedByOwner;
    private boolean hasPassword;
    private boolean singleLink;
    private AnnonDeleteToken deleteToken;
    private QuickOwned quickOwned;
    private BouncerStatus bouncerStatus;



    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getObjSeq()
    {
        return objSeq;
    }

    public void setObjSeq(String objSeq)
    {
        this.objSeq = objSeq;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public long getGoodTillTime() {
        return goodTillTime;
    }

    public void setGoodTillTime(long goodTillTime) {
        this.goodTillTime = goodTillTime;
    }

    public boolean isAllowViewAfterGtt() {
        return allowViewAfterGtt;
    }

    public void setAllowViewAfterGtt(boolean allowViewAfterGtt) {
        this.allowViewAfterGtt = allowViewAfterGtt;
    }

    public boolean isBlank() {
        return blank;
    }

    public void setBlank(boolean b) {
        blank = b;
    }

    public boolean isAccessedByOwner() {
        return accessedByOwner;
    }

    public void setAccessedByOwner(boolean accessedByOwner) {
        this.accessedByOwner = accessedByOwner;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public boolean isSingleLink() {
        return singleLink;
    }

    public void setSingleLink(boolean singleLink) {
        this.singleLink = singleLink;
    }

    public AnnonDeleteToken getDeleteToken() {
        return deleteToken;
    }

    public void setDeleteToken(AnnonDeleteToken deleteToken) {
        this.deleteToken = deleteToken;
    }

    public int getCl1pType() {
        return cl1pType;
    }

    public void setCl1pType(int cl1pType) {
        this.cl1pType = cl1pType;
    }

    public void setOwned(QuickOwned owned) {
        this.quickOwned = owned;
    }

    public QuickOwned getOwned() {
        return quickOwned;
    }

    public BouncerStatus getBouncerStatus() {
        return bouncerStatus;
    }

    public void setBouncerStatus(BouncerStatus bouncerStatus) {
        this.bouncerStatus = bouncerStatus;
    }
}
