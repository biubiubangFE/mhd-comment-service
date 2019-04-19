package com.mhdss.comment.constant;


import java.util.List;

public class CommentVO {

    private Long commentId;

    private TenantUserVO userVO;

    private Long userId;

    private String commentValue;

    private List<CommentVO> chileComment;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public TenantUserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(TenantUserVO userVO) {
        this.userVO = userVO;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCommentValue() {
        return commentValue;
    }

    public void setCommentValue(String commentValue) {
        this.commentValue = commentValue;
    }

    public List<CommentVO> getChileComment() {
        return chileComment;
    }

    public void setChileComment(List<CommentVO> chileComment) {
        this.chileComment = chileComment;
    }
}
