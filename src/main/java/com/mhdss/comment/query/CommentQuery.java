package com.mhdss.comment.query;

public class CommentQuery extends AbstractQuery {

    private Long tenantId;

    private Long tenantResourceId;

    private Long id;

    private Long userId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}