package com.eepl.lab_back.common;

public interface ResponseCode {


    // HTTP STATUS 200
    String SUCCESS = "SU";

    // HTTP STATUS 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_ID = "DI";

    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_PHONE = "DP";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_BOARD = "NB";

    // HTTP STATUS 401
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";

    // HTTP STATUS 403
    String NO_PERMISSION = "NP";
    String ACCOUNT_PENDING = "AP";

    // HTTP STATUS 500
    String DATABASE_ERROR = "DBE";


}
