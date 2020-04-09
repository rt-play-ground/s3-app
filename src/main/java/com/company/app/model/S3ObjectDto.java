package com.company.app.model;

/* rtempalli created on 4/9/20 inside the package - com.company.app.model */
public class S3ObjectDto {
    String key;
    String value;

    public S3ObjectDto(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
