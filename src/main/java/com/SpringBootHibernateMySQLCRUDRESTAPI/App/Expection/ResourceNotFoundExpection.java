package com.SpringBootHibernateMySQLCRUDRESTAPI.App.Expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.Field;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExpection extends RuntimeException {
    private String fieldName;
    private String resourcesName;
    private Object fieldValue;

    public ResourceNotFoundExpection( String fieldName, String resourcesName, Object fieldValue) {

        super(String.format("%s not found %s : %s", resourcesName, fieldName, fieldValue));
        this.fieldName = fieldName;
        this.resourcesName = resourcesName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
