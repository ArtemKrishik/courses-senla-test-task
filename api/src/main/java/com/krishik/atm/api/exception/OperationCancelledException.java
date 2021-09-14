package com.krishik.atm.api.exception;

public class OperationCancelledException extends RuntimeException {

    public OperationCancelledException(String message) {
        super(message);
    }
}
