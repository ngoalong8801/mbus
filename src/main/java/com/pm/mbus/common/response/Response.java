package com.pm.mbus.common.response;

import com.pm.mbus.common.HttpStatus;

public class Response<T> {
    protected T body;
    protected HttpStatus status;
    protected int code;

    public Response(T body, HttpStatus status, int code) {
        this.body = body;
        this.status = status;
        this.code = code;
    }

    public Response() {
    }

    @Override
    public String toString() {
        return "Response{" +
                "body=" + body +
                ", status=" + status +
                ", code=" + code +
                '}';
    }
}
