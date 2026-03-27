package com.matheusgondra.md2pdf.exception;

public class RequiredArgumentException extends RuntimeException {
    public RequiredArgumentException(String argument) {
        super("Required argument is missing: " + argument);
    }

    public RequiredArgumentException(Throwable cause) {
        super("Required argument is missing", cause);
    }
}