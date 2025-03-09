package com.robmayhew.cl1p.web;

import com.robmayhew.cl1p.Cl1p;
import com.robmayhew.cl1p.Constants;
import com.robmayhew.cl1p.area.bouncer.BouncerRequest;
import com.robmayhew.cl1p.area.bouncer.BouncerStatus;
import com.robmayhew.cl1p.area.ownership.database.OwnedDatabaseController;
import com.robmayhew.cl1p.area.ownership.wrapper.QuickOwned;
import com.robmayhew.cl1p.control.message.Cl1pAction;
import com.robmayhew.cl1p.control.message.Path;
import com.robmayhew.cl1p.open.objects.Page;
import com.robmayhew.cl1p.util.Cl1pStringUtil;
import com.robmayhew.cl1p.web.publicrest.apitoken.ApiToken;
import com.robmayhew.cl1p.web.publicrest.apitoken.ApiTokenStorage;
import com.robmayhew.cl1p.web.rest.object.RestCl1pWrite;
import com.robmayhew.cl1p.web.rest.object.RestViewCl1p;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Cl1pRequest
{

    private final String content;
    private final String pageHash;
    private final String seqHash;
    private final Path path;
    private final Page pageIn;
    private final boolean post;
    private final int ttl;
    private final String userAgent;
    private final String remoteAddress;
    private final Cl1pSession session;
    private final ApiToken token;
    private Cl1pAction action;
    private JSP jsp;
    private String password;
    private int passwordOption;
    private boolean apiRequest;

    private int cl1pType;
    private final QuickOwned owned;
    private BouncerStatus bouncerStatus;
    private BouncerRequest bouncerRequest;


    public Cl1pRequest(String content,
                       String pageHash,
                       String seqHash,
                       Path path,
                       boolean post,
                       int ttl,
                       String userAgent,
                       String remoteAddress,
                       Cl1pSession session,
                       Cl1pAction action,
                       String password,
                       int passwordOption,
                       int cl1pType,
                       QuickOwned owned,
                       ApiToken token)
    {
        this.content = content;
        this.pageHash = pageHash;
        this.seqHash = seqHash;
        this.path = path;
        this.post = post;
        this.userAgent = userAgent;
        this.remoteAddress = remoteAddress;
        this.ttl = ttl;
        this.cl1pType = cl1pType;
        pageIn = new Page();
        pageIn.setPath(path.getPath());
        pageIn.setContent(content);
        pageIn.setObjId(pageHash);
        pageIn.setObjSeq(seqHash);
        pageIn.setGoodTillTime(Cl1p.getTimeControl().calcTtl(ttl));
        pageIn.setAllowViewAfterGtt(ttl == 0);
        pageIn.setCl1pType(cl1pType);
        pageIn.setOwned(owned);
        this.session = session;
        this.action = action;
        this.password = password;
        this.passwordOption = passwordOption;
        this.owned = owned;
        this.token = token;
    }

    public Cl1pRequest(String body, Cl1pRequest cr) {
        content = body;
        this.pageHash = cr.pageHash;
        this.seqHash = cr.seqHash;
        this.path = cr.path;
        this.post = cr.post;
        this.userAgent = cr.userAgent;
        this.remoteAddress = cr.remoteAddress;
        this.ttl = cr.ttl;
        pageIn = new Page();
        pageIn.setPath(cr.path.getPath());
        pageIn.setContent(content);
        pageIn.setObjId(cr.pageHash);
        pageIn.setObjSeq(cr.seqHash);
        pageIn.setGoodTillTime(Cl1p.getTimeControl().calcTtl(cr.ttl));
        pageIn.setAllowViewAfterGtt(cr.ttl == 0);
        this.session = cr.session;
        this.action = cr.action;
        this.password = cr.password;
        this.passwordOption = cr.passwordOption;
        this.apiRequest = cr.apiRequest;
        this.owned = cr.owned;
        this.token  = cr.token;
    }

    public static Cl1pRequest fromServletRequest(HttpServletRequest req) {
        boolean post = "POST".equals(req.getMethod());

        int ttl = 0;
        String ttlStr = req.getParameter("ttl");
        if (Cl1pStringUtil.isSet(ttlStr)) {
            try {
                ttl = Integer.parseInt(ttlStr);
                if (ttl < 0)
                    ttl = 0;
                if (ttl > 525600)
                    ttl = 525600;
            } catch (NumberFormatException e) {
                // Ignore;
            }
        }

        String content = req.getParameter("content");
        String userAgent = req.getHeader("User-Agent");
        String remoteAddress = req.getRemoteAddr();

        String pageHash = req.getParameter("pageHash");
        String seqHash = req.getParameter("seqHash");
        Cl1pAction action = Cl1pAction.fromCode(req.getParameter("action"));
        if (Cl1pStringUtil.isNotSet(pageHash))
            pageHash = "";
        if (Cl1pStringUtil.isNotSet(seqHash))
            seqHash = "";

        String plainPassword = req.getParameter("password");
        String password = null;

        if (Cl1pStringUtil.isSet(plainPassword)) {
            if (post) {
                if (!plainPassword.equals(req.getParameter("passwordVerify")))
                    plainPassword = null; // Auto complete sometimes fills this in
            }
        }
        if (Cl1pStringUtil.isSet(plainPassword)) {
            password = Cl1pStringUtil.hash(plainPassword);
        }

        String plainPasswordOption = req.getParameter("passwordOption");
        int passwordOption = 0;
        if (Cl1pStringUtil.isSet(plainPasswordOption)) {
            try {
                passwordOption = Integer.parseInt(plainPassword);
            } catch (NumberFormatException ignored) {
            }
        }

        int cl1pType = Constants.CL1P_TYPE_PLAIN;
        String cl1pTypeString = req.getParameter("cl1pType");
        if (Cl1pStringUtil.isSet(cl1pTypeString)) {
            try {
                cl1pType = Integer.parseInt(cl1pTypeString);
            } catch (NumberFormatException ignored) {
            }
        }

        Path path = new Path(req.getRequestURI());
        Cl1pSession session = Cl1pSession.session(req);
        OwnedDatabaseController odc = Cl1p.getOwnedDatabaseController();
        QuickOwned owned = odc.loadQuickOwned(req.getRequestURI());
        if(owned != null && owned.isNeverDelete())
            ttl = Integer.MAX_VALUE;
        ApiTokenStorage tokenStorage = Cl1p.getApiTokenStorage();
        ApiToken token = tokenStorage.loadTokenByToken(req.getHeader(Constants.API_TOKEN_HEADER));
        return new Cl1pRequest(content, pageHash, seqHash, path, post, ttl, userAgent, remoteAddress, session, action, password, passwordOption, cl1pType,owned, token);
    }

    public static Cl1pRequest fromServletApiRequest(HttpServletRequest request) throws IOException {
        Cl1pRequest cl1pRequest = fromServletRequest(request);
        Cl1p.getBouncer().populate(cl1pRequest);
        cl1pRequest.setApiRequest(true);
        if (cl1pRequest.isPost()) {
            String body = getBody(request);
            return new Cl1pRequest(body, cl1pRequest);
        }
        return cl1pRequest;
    }

    public static Cl1pRequest fromRestApiRequest(RestViewCl1p viewCl1p, HttpServletRequest request) {
        Cl1pSession session = Cl1pSession.session(request);
        OwnedDatabaseController odc = Cl1p.getOwnedDatabaseController();
        QuickOwned owned = odc.loadQuickOwned(viewCl1p.getPath());
        ApiTokenStorage tokenStorage = Cl1p.getApiTokenStorage();
        ApiToken token = tokenStorage.loadTokenByToken(request.getHeader(Constants.API_TOKEN_HEADER));

        Cl1pRequest result = new Cl1pRequest(null,
                null,
                null,
                new Path(viewCl1p.getPath()),
                false,
                0,
                request.getHeader("User-Agent"),
                request.getRemoteAddr(),
                session,
                null,
                viewCl1p.getPassword(),
                0,
                Constants.CL1P_TYPE_PLAIN,
                owned,
                token
        );
        return result;
    }

    public static Cl1pRequest fromRestApiRequest(RestCl1pWrite cl1pWrite, HttpServletRequest request) {
        Cl1pSession session = Cl1pSession.session(request);
        OwnedDatabaseController odc = Cl1p.getOwnedDatabaseController();
        QuickOwned owned = odc.loadQuickOwned(cl1pWrite.getPath());
        ApiTokenStorage tokenStorage = Cl1p.getApiTokenStorage();
        ApiToken token = tokenStorage.loadTokenByToken(request.getHeader(Constants.API_TOKEN_HEADER));
        Cl1pRequest result = new Cl1pRequest(cl1pWrite.getContent(),
                null,
                null,
                new Path(cl1pWrite.getPath()),
                true,
                cl1pWrite.getTtl(),
                request.getHeader("User-Agent"),
                request.getRemoteAddr(),
                session,
                null,
                cl1pWrite.getPassword(),
                0,
                Constants.CL1P_TYPE_PLAIN,
                owned,
                token
        );
        return result;
    }

    public static String getBody(HttpServletRequest request) throws IOException {

        String body;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    public String getContent() {
        return content;
    }

    public String getPageHash() {
        return pageHash;
    }

    public String getSeqHash()
    {
        return seqHash;
    }

    public Page getPageIn()
    {
        return pageIn;
    }

    public Path getPath()
    {
        return path;
    }

    public JSP getJsp() {
        return jsp;
    }

    public void setJsp(JSP jsp) {
        this.jsp = jsp;
    }

    public boolean isPost() {
        return post;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public boolean hasContent()
    {
        return Cl1pStringUtil.isSet(content);
    }

    public Cl1pSession getSession() {
        return session;
    }

    public int getTtl() {
        return ttl;
    }

    public Cl1pAction getAction() {
        return action;
    }

    public void setAction(Cl1pAction action) {
        this.action = action;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPasswordOption() {
        return passwordOption;
    }

    public void setPasswordOption(int passwordOption) {
        this.passwordOption = passwordOption;
    }

    public boolean isApiRequest() {
        return apiRequest;
    }

    public void setApiRequest(boolean apiRequest) {
        this.apiRequest = apiRequest;
    }

    public int getCl1pType() {
        return cl1pType;
    }

    public QuickOwned getOwned() {
        return owned;
    }

    public ApiToken getToken() {
        return token;
    }

    public BouncerStatus getBouncerStatus() {
        return bouncerStatus;
    }

    public void setBouncerStatus(BouncerStatus bouncerStatus) {
        this.bouncerStatus = bouncerStatus;
    }

    public BouncerRequest getBouncerRequest() {
        return bouncerRequest;
    }

    public void setBouncerRequest(BouncerRequest bouncerRequest) {
        this.bouncerRequest = bouncerRequest;
    }

    public String toDebugString() {
        return "Cl1pRequest{" +
                "content='" + content + '\'' +
                ", pageHash='" + pageHash + '\'' +
                ", seqHash='" + seqHash + '\'' +
                ", path=" + path +
                ", pageIn=" + pageIn +
                ", post=" + post +
                ", ttl=" + ttl +
                ", userAgent='" + userAgent + '\'' +
                ", remoteAddress='" + remoteAddress + '\'' +
                ", session=" + session +
                ", token=" + token +
                ", action=" + action +
                ", jsp=" + jsp +
                ", password='" + password + '\'' +
                ", passwordOption=" + passwordOption +
                ", apiRequest=" + apiRequest +
                ", cl1pType=" + cl1pType +
                ", owned=" + owned +
                ", bouncerStatus=" + bouncerStatus +
                ", bouncerRequest=" + bouncerRequest +
                '}';
    }
}
