package com.mhdss.comment.constant;

public enum  CommentTopType {

    TOP((byte) -1, "顶层"),
    LOVER((byte) 1, "下层");

    private final Byte type;
    private final String desc;

    CommentTopType(Byte type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static CommentTopType getStatus(Byte status) {

        for (CommentTopType type : CommentTopType.values()) {
            if (status.equals(type.getType())) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }

    public Byte getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
