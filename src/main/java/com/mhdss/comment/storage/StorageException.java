package com.mhdss.comment.storage;

public class StorageException extends RuntimeException {

    public StorageException(Throwable e) {
        super(e);
    }

    public StorageException(String msg) {
        super(msg);
    }
}
