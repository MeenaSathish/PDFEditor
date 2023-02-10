package com.allioc.pdfeditor.service;

import com.allioc.pdfeditor.service.field.IFieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FieldFactory {
    @Autowired
    private List<IFieldType> fieldTypes;



    private static final Map<String, IFieldType> fieldCache = new HashMap<>();

    @PostConstruct
    public void initFieldCache() {
        for (IFieldType fieldType: fieldTypes) {
            fieldCache.put(fieldType.getType(), fieldType);
        }
    }

    public IFieldType getField(String type) {
        IFieldType fieldType = fieldCache.get(type);
        if (fieldType == null) {
            throw new RuntimeException("Unknown Service Type:" + fieldType);
        }
        return fieldType;
    }
}
