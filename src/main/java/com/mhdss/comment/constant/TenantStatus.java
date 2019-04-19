package com.mhdss.comment.constant;


//租户过期状态
public enum TenantStatus {

    NORMAL((byte) 0, "正常"),
    EXPIRE((byte) 1, "过期"),
    ERROR((byte) 2, "异常");

    private final Byte status;
    private final String desc;

    TenantStatus(Byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static TenantStatus getStatus(Byte status) {

        for (TenantStatus baseStatus : TenantStatus.values()) {
            if (status.equals(baseStatus.getStatus())) {
                return baseStatus;
            }
        }
        throw new IllegalArgumentException();
    }

    public Byte getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
