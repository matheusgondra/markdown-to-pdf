package com.matheusgondra.md2pdf.service;

import org.springframework.stereotype.Service;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;

@Service
public class MarkdownParser {
    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownParser() {
        this.parser = Parser.builder().build();
        this.renderer = HtmlRenderer.builder().build();
    }

    public String parseToHTML(String markdown) {
        Document document = parser.parse(markdown);
        return renderer.render(document);
    }
}
