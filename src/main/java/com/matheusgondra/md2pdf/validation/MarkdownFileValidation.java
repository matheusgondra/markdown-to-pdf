package com.matheusgondra.md2pdf.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.matheusgondra.md2pdf.exception.InvalidFileException;
import com.matheusgondra.md2pdf.exception.RequiredArgumentException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MarkdownFileValidation {
    public void validate(MultipartFile file) {
        log.debug("Iniciando validação");
        if (file == null) {
            throw new RequiredArgumentException("file");
        }

        if (file.isEmpty()) {
            throw new RequiredArgumentException("file");
        }

        String originalFilename = file.getOriginalFilename();
        log.debug("originalFilename: {}", originalFilename);
        if (originalFilename == null || originalFilename.trim().isEmpty()) {
            throw new RequiredArgumentException("filename");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        log.debug("File extension: {}", extension);
        boolean isMarkdown = originalFilename.toLowerCase().endsWith(".md");
        if (!isMarkdown) {
            throw new InvalidFileException();
        }
    }
}
