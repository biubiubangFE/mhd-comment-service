package com.mhdss.comment.dataobject;

public class CommentDO extends BaseDO {
    private Long productId;

    private Long userId;

    private Byte topType;

    private Long parentId;

    private String commentValue;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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