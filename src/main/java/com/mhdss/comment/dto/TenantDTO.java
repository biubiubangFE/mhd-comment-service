package com.mhdss.comment.dto;


import com.mhdss.comment.constant.TenantStatus;

public class TenantDTO {

    private Long id;

    private String code;

    private String checkUrl;

    private TenantStatus tenantStatus;

    private Long endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public TenantStatus getTenantStatus() {
        return tenantStatus;
    }

    public void setTenantStatus(TenantStatus tenantStatus) {
        this.tenantStatus = tenantStatus;
    }
}
