package com.pet.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    PET_NOT_FOUND("你找的该宠物不存在了，要不看看其它的"),
    FILE_UPLOAD_FAIL("上传出错了");
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }

}
