package com.allioc.pdfeditor.service.field;

import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class CheckBox implements IFieldType {

    private static List<String> CHECKBOX_NAMES = Arrays.asList("M","F","Y","N","Plan Formulary","Summary of Benefits",
                "Evidence of Coverage","Get a Bill","Electronic funds transfer (EFT) from your bank account each month",
                "Checking","Savings","Credit Card",
                "Automatic deduction from your monthly Social Security/Railroad Retirement Board (RRB) benefit check.",
                "Social Security","RRB","I understand that:","I understand that my signature (or the signature of the person legally authorized to act on my\n" +
                    "behalf) on this application means that I have read and understand the contents of this application.\n" +
                    "If signed by authorized representative (as described above), this signature certifies that:");
    public String getType() {
        return "PDNonTerminalField";
    }

    public void setValue(PDField field, String value) throws IOException {
        if (field != null) {
            PDNonTerminalField nonTerminalField = (PDNonTerminalField) field;
            // System.out.println("Setting the value" + nonTerminalField.getChildren() + ", value:" + value);
            for (PDField f : nonTerminalField.getChildren()) {
                if (CHECKBOX_NAMES.contains(f.getPartialName())) {
                    try {
                        f.setValue("On");
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
