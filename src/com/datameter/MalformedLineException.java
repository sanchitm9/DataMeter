package com.datameter;

// Custom exception to indicate a malformed line in the input data
public class MalformedLineException extends Exception {
    // Passes the error message to the base Exception class
    public MalformedLineException(String message) {
        super(message);
    }
}