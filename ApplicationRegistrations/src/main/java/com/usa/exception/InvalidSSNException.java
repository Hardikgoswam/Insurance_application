package com.usa.exception;

public class InvalidSSNException extends Exception { // Use Exception or RuntimeException

    // Default constructor
    public InvalidSSNException() {
        super("Invalid SSN provided.");
    }

    // Constructor with custom message
    public InvalidSSNException(String msg) {
        super(msg);
    }
}
