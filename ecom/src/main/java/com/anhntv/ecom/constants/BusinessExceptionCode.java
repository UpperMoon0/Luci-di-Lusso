package com.anhntv.ecom.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
public enum BusinessExceptionCode {

    NO_CODE(0, "No code", INTERNAL_SERVER_ERROR),

    ACCOUNT_NOT_FOUND(1001, "Account not found", NOT_FOUND),

    ACCOUNT_LOCKED(1002, "Account locked", FORBIDDEN),

    ACCOUNT_DISABLED(1003, "Account disabled", FORBIDDEN),

    ACCOUNT_EXPIRED(1004, "Account expired", FORBIDDEN),

    ACCOUNT_CREDENTIALS_EXPIRED(1005, "Account credentials expired", FORBIDDEN),

    BAD_CREDENTIALS(1006, "Bad credentials", UNAUTHORIZED),

    INVALID_TOKEN(1007, "Invalid token", UNAUTHORIZED),

    TOKEN_EXPIRED(1008, "Token expired", UNAUTHORIZED),

    INCORRECT_CURRENT_PASSWORD(300, "Incorrect current password", BAD_REQUEST),

    PASSWORDS_DO_NOT_MATCH(301, "Passwords do not match", BAD_REQUEST),
    ;

    private final int code;

    private final String description;

    private final HttpStatus httpStatus;

    BusinessExceptionCode(int code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
