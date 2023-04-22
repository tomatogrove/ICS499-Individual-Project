package com.team4.model.util;

public class UserSessionDTO {

    private Long sessionID;

    private String sessionKey;

    private Long userAccountID;

    public UserSessionDTO() { }

    public UserSessionDTO(Long sessionID, String sessionKey, Long userAccountID) {
        this.sessionID = sessionID;
        this.sessionKey = sessionKey;
        this.userAccountID = userAccountID;
    }

    public Long getSessionID() {
        return sessionID;
    }

    public void setSessionID(Long sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Long getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(Long userAccountID) {
        this.userAccountID = userAccountID;
    }
}
