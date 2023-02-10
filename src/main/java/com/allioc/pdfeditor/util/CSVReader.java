package com.allioc.pdfeditor.util;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    public List<String[]> read(String fileName) throws Exception {
        List<String[]> data = null;
        try (com.opencsv.CSVReader reader = new com.opencsv.CSVReader(new FileReader(fileName))) {
            data = reader.readAll();
           // data.forEach(x -> System.out.println(Arrays.toString(x)));
        }
        return data;
    }
}
