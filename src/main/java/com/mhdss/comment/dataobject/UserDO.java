package com.mhdss.comment.dataobject;

public class UserDO extends BaseDO {
    private Long tanantId;

    private Long tanantUserId;

    private String nickName;

    private String avatarUrl;

    public Long getTanantId() {
        return tanantId;
    }

    public void setTanantId(Long tanantId) {
        this.tanantId = tanantId;
    }

    public Long getTanantUserId() {
        return tanantUserId;
    }

    public void setTanantUserId(Long tanantUserId) {
        this.tanantUserId = tanantUserId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}