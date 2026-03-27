package com.matheusgondra.md2pdf.controller.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.matheusgondra.md2pdf.exception.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Operation(summary = "Convert Markdown to PDF", description = "Converts an uploaded Markdown file to PDF format and returns it as PDF file download.")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "PDF file generated successfully", headers = {
                @Header(name = "Content-Type", description = "application/pdf", schema = @Schema(type = "string")),
                @Header(name = "Content-Disposition", description = "attachment; filename=arquivo.pdf", schema = @Schema(type = "string"))
        }, content = @Content(mediaType = "application/pdf", schema = @Schema(type = "string", format = "binary"))),
        @ApiResponse(responseCode = "400", description = "Invalid file format or missing file", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "413", description = "Uploaded file exceeds the maximum allowed size"),
        @ApiResponse(responseCode = "500", description = "Internal server error occurred during conversion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ConvertMarkdownToPdfControllerDoc {

}
