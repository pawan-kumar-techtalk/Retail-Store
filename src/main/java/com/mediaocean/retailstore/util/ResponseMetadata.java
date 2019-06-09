package com.mediaocean.retailstore.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResponseMetadata {

    private List<String> generalErrors;

    public ResponseMetadata() {
        this(new ArrayList<String>());
    }

    public ResponseMetadata(List<String> generalErrors) {
        this.generalErrors = generalErrors;
    }

    public List<String> getGeneralErrors() {
        return generalErrors;
    }

    public void setGeneralErrors(List<String> generalErrors) {
        this.generalErrors = generalErrors;
    }

    public void addGeneralError(String errorMessage) {
        this.generalErrors.add(errorMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseMetadata that = (ResponseMetadata) o;
        return Objects.equals(generalErrors, that.generalErrors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generalErrors);
    }

    @Override
    public String toString() {
        return "ResponseMetadata{" +
                "generalErrors=" + generalErrors +
                '}';
    }
}