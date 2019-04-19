package com.mhdss.comment.exception;

public class HeaderMissException extends RuntimeException {

    private String sessionKey;

    private String tenantCode;

    public HeaderMissException(String sessionKey, String tenantCode) {
        this.sessionKey = sessionKey;
        this.tenantCode = tenantCode;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }
}
