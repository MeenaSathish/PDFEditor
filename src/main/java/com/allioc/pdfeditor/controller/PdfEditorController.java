package com.allioc.pdfeditor.controller;

import com.allioc.pdfeditor.service.PdfEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfEditorController {

    @Autowired
    private PdfEditorService editor;


    @PostMapping("/v1/pdf/fill")
    public ResponseEntity<String> fillForm(@RequestParam("datafileName") String dataFileName) throws Exception {
        String fileName = editor.populateDataInPDF(dataFileName);
        return ResponseEntity.ok().body("PDF File " + fileName +" updated successfully ");
    }
}
