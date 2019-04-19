package com.mhdss.comment.constant;

import com.mhdss.comment.dataobject.UserDO;

public class TenantUserVO {

    private Long tanantUserId;

    private String nickName;

    private String avatarUrl;

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
