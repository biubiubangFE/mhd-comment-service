package com.mhdss.comment.dataobject;

public class CommentDO extends BaseDO {
    private Long tenantId;

    private Long tenantResourceId;

    private Long userId;

    private Byte topType;

    private Long parentId;

    private String commentValue;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getTenantResourceId() {
        return tenantResourceId;
    }

    public void setTenantResourceId(Long tenantResourceId) {
        this.tenantResourceId = tenantResourceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getTopType() {
        return topType;
    }

    public void setTopType(Byte topType) {
        this.topType = topType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCommentValue() {
        return commentValue;
    }

    public void setCommentValue(String commentValue) {
        this.commentValue = commentValue;
    }
}