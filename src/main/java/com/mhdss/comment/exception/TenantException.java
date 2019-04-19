package com.mhdss.comment.exception;

public class TenantException extends RuntimeException{

    private Byte tenantStatus;

    public TenantException(String message, Byte tenantStatus) {
        super(message);
        this.tenantStatus = tenantStatus;
    }

    public Byte getTenantStatus() {
        return tenantStatus;
    }

    public void setTenantStatus(Byte tenantStatus) {
        this.tenantStatus = tenantStatus;
    }
}
