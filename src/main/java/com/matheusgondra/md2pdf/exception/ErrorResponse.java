package com.matheusgondra.md2pdf.exception;

import java.time.LocalDateTime;

public record ErrorResponse(String message, int statusCode, LocalDateTime timestamp) {

    public static ErrorResponse of(String message, int statusCode) {
        return new ErrorResponse(message, statusCode, LocalDateTime.now());
    }

}
