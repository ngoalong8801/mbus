package com.pm.mbus.common.response;

import com.pm.mbus.common.HttpStatus;

public interface ResponseBuilder<T> {
    ResponseBuilder<T> withHttpCode(int httpCode);
    ResponseBuilder<T> withHttpStatus(HttpStatus httpStatus);
    ResponseBuilder<T> withBody(T body);
    Response<T> build();
    Response<T> buildErrorResponse();
    Response<T> buildSuccessResponse();
}
