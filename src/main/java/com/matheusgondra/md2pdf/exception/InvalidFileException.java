package com.matheusgondra.md2pdf.exception;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException() {
        super("Invalid file. Only Markdown files with .md extension are allowed.");
    }
}
