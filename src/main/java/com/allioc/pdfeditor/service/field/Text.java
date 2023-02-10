package com.allioc.pdfeditor.service.field;

import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Text implements IFieldType{

    public String getType() {
        return "PDTextField";
    }

    public void setValue(PDField field, String val) throws IOException {
        if (field != null ){
            PDTextField textField = (PDTextField) field;

            textField.setDefaultAppearance(textField.getAcroForm().getDefaultAppearance());

            textField.setValue(val);
        }
    }
}
