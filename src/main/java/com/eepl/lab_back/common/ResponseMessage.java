package com.eepl.lab_back.common;

public interface ResponseMessage {


    // HTTP STATUS 200
    String SUCCESS = "Success.";

    // HTTP STATUS 400
    String VALIDATION_FAILED = "Validation Failed";
    String DUPLICATE_ID = "Duplicate Id";
    String DUPLICATE_EMAIL = "Duplicate Email";
    String INVALID_EMAIL = "Invalid Email";
    String DUPLICATE_PHONE = "Duplicate PhoneNumber";
    String NOT_EXISTED_USER = "This user does not exist.";
    String NOT_EXISTED_BOARD = "This board does not exist.";

    // HTTP STATUS 401
    String SIGN_IN_FAIL = "Login information mismatch.";
    String AUTHORIZATION_FAIL = "Authorization Failed.";

    // HTTP STATUS 403
    String NO_PERMISSION = "Do not have permission.";
    String ACCOUNT_PENDING = "Account pending approval.";

    // HTTP STATUS 500
    String DATABASE_ERROR = "Database Error.";

}
