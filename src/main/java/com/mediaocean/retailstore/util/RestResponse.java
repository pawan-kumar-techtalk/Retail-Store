package com.mediaocean.retailstore.util;

import java.util.Objects;

public class RestResponse {

    private ResponseMetadata meta = new ResponseMetadata();

    private Object data;

    public RestResponse() {
    }

    public RestResponse(Object data) {
        this.data = data;
    }

    public RestResponse(String errorMessage) {
        this.meta.addGeneralError(errorMessage);
    }

    public void addGeneralError(String errorMessage) {
        meta.addGeneralError(errorMessage);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseMetadata getMeta() {
        return meta;
    }

    public void setMeta(ResponseMetadata meta) {
        this.meta = meta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestResponse that = (RestResponse) o;
        return Objects.equals(meta, that.meta) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meta, data);
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
