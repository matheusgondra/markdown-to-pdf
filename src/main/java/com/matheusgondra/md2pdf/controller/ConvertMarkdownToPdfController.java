package com.matheusgondra.md2pdf.controller;

import java.util.Objects;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.matheusgondra.md2pdf.controller.doc.ConvertMarkdownToPdfControllerDoc;
import com.matheusgondra.md2pdf.service.PdfService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Markdown to PDF", description = "Endpoints for converting Markdown files to PDF format")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/markdown-to-pdf")
public class ConvertMarkdownToPdfController {
    private final PdfService service;

    @ConvertMarkdownToPdfControllerDoc
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> handle(@RequestParam("file") MultipartFile file) {
        try {
            byte[] pdfBytes = service.execute(file);
            String outputName = Objects.requireNonNull(file.getOriginalFilename()).replace(".md", ".pdf");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment().filename(outputName).build());

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
