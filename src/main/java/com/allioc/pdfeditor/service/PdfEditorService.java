package com.allioc.pdfeditor.service;

import com.allioc.pdfeditor.util.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class PdfEditorService {
    /**
     * Read the data from the CSV file
     * Populate the data in the PDF which is stored in the resources folder
     */

    @Autowired
    private FieldFactory fieldFactory;

    public String populateDataInPDF(String dataFile) throws Exception {
        CSVReader reader = new CSVReader();
        List<String[]> csvData = reader.read(dataFile);

        // First line in the CSV is used in getting the text fields from pdf
        List<String> hdr = Arrays.asList(csvData.get(0));
        List<String> data = Arrays.asList(csvData.get(1));

        String formTemplate = "src/main/resources/SampleForm.pdf";

        try (PDDocument pdfDocument = Loader.loadPDF(new File(formTemplate))) {
            // get the document catalog
            PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

            if (acroForm != null) {
                // Retrieve an individual field and set its value.
                List<PDField> fields = acroForm.getFields();
//                fields.forEach(f -> System.out.println(f.getPartialName()));
//                PDNonTerminalField field = (PDNonTerminalField) acroForm.getField( "EmailOptIn" );
                IntStream.range(0, hdr.size())
                    .forEach((i -> fields.stream().forEach(f -> {
                    if (f.getPartialName().equals(hdr.get(i))) {
                        log.info("Field Name:{}", f.getPartialName(), " value:{}", data.get(i));
                        try {
                            fieldFactory.getField(f.getClass().getSimpleName()).setValue(f, data.get(i));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })));
            } else {
                log.info("Check it out");
            }

            // Save and close the filled out form.
            pdfDocument.save("src/main/resources/FillFormField.pdf");
            return "src/main/resources/FillFormField.pdf";
        }
    }

}
