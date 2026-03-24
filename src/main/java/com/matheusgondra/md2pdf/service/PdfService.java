package com.matheusgondra.md2pdf.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class PdfService {
    public byte[] execute(MultipartFile file) {
        log.info("Processing file: {}", file.getOriginalFilename());

        try (var outputStream = new ByteArrayOutputStream()) {
            String markdownContent = new String(file.getBytes(), StandardCharsets.UTF_8);

            MutableDataSet options = new MutableDataSet();
            Parser parser = Parser.builder(options).build();
            HtmlRenderer renderer = HtmlRenderer.builder(options).build();

            String bodyContent = renderer.render(parser.parse(markdownContent));
            String html = new StringBuilder()
                    .append("<!DOCTYPE html> <html lang=\"pt-br\"> <head>")
                    .append("<meta charset=\"UTF-8\"/>")
                    .append("<style>")
                    .append("body { font-family: 'Arial', sans-serif; line-height: 1.6; margin: 50px; color: #333; }")
                    .append("h1 { color: #2c3e50; border-bottom: 2px solid #3498db; padding-bottom: 10px; }")
                    .append("h2 { color: #2980b9; margin-top: 25px; border-left: 5px solid #3498db; padding-left: 10px; }")
                    .append("li { margin-bottom: 10px; }")
                    .append("a { color: #3498db; text-decoration: underline; }")
                    .append("strong { color: #e74c3c; }")
                    .append("</style></head><body>")
                    .append(bodyContent)
                    .append("</body></html>")
                    .toString();

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
