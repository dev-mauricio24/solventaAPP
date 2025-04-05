package com.solventa.user.util;

import com.solventa.user.util.constant.UserConstants;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
	USER_NOT_FOUND(UserConstants.USER_NOT_FOUND_CODE, UserConstants.USER_NOT_FOUND),
    USER_CONNECT_TIME_OUT(UserConstants.USER_CONNECT_TIME_OUT_CODE, UserConstants.TIME_OUT_ERROR),
    USER_SERVER_ERROR(UserConstants.USER_SERVER_ERROR_CODE, UserConstants.SERVER_ERROR);

    private final String code;
    private final String message;


    private ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
