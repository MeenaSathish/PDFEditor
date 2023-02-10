package com.allioc.pdfeditor.service.field;

import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.IOException;

public interface IFieldType {

    public String getType();

    public void setValue(PDField field, String val) throws IOException;
}
