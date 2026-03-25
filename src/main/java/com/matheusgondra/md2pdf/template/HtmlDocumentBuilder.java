package com.matheusgondra.md2pdf.template;

import java.util.Objects;

public class HtmlDocumentBuilder {
    private static final String DEFAULT_CSS = """
            body {
                font-family: "Arial", sans-serif;
                line-height: 1.6;
                margin: 50px;
                color: #333;
            }

            h1 {
                color: #2c3e50;
                border-bottom: 2px solid #3498db;
                padding-bottom: 10px;
            }

            h2 {
                color: #2980b9;
                margin-top: 25px;
                border-left: 5px solid #3498db;
                padding-left: 10px;
            }

            li {
                margin-bottom: 10px;
            }

            a {
                color: #3498db;
                text-decoration: underline;
            }

            strong {
                color: #e74c3c;
            }
            """;

    public static String build(String bodyContent) {
        String body = Objects.requireNonNull(bodyContent, "");

        return """
                <!DOCTYPE html>
                <html lang="pt-br">
                <head>
                    <meta charset="UTF-8"/>
                    <style>%s</style>
                </head>
                <body>
                %s
                </body>
                </html>
                 """.formatted(DEFAULT_CSS, body);
    }
}
