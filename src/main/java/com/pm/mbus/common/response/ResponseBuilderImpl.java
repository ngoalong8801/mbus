package com.pm.mbus.common.response;

import com.pm.mbus.common.HttpStatus;

public class ResponseBuilderImpl<T> extends Response<T> implements ResponseBuilder<T> {
    @Override
    public ResponseBuilder<T> withHttpCode(int httpCode) {
        this.code = httpCode;
        return this;
    }

    @Override
    public ResponseBuilder<T> withHttpStatus(HttpStatus httpStatus) {
        this.status = httpStatus;
        return this;
    }

    @Override
    public ResponseBuilder<T> withBody(T body) {
        this.body = body;
        return this;
    }

    @Override
    public Response<T> build() {
        return new Response<>(body, status, code);
    }

    @Override
    public Response<T> buildSuccessResponse() {
        return new Response<>(body, HttpStatus.SUCCESS, 200);
    }
    @Override
    public Response<T> buildErrorResponse() {
        return new Response<>(body, HttpStatus.FAIL, 404);
    }
}
