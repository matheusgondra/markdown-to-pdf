package com.matheusgondra.md2pdf.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.matheusgondra.md2pdf.parser.MarkdownParser;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PdfService {
    private final MarkdownParser markdownParser;

    public byte[] execute(MultipartFile file) {
        log.info("Processing file: {}", file.getOriginalFilename());

        try (var outputStream = new ByteArrayOutputStream()) {
            String markdownContent = new String(file.getBytes(), StandardCharsets.UTF_8);

            String html = markdownParser.parseToHTML(markdownContent);

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, "/");
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();
        } catch (IOException e) {
            log.error("Error processing file: {}", file.getOriginalFilename(), e);
            throw new RuntimeException(e);
        }
    }
}
