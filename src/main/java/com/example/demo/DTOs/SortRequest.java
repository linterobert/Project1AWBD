package com.example.demo.DTOs;

public class SortRequest {
    private String field_name;
    private Boolean ascending;

    public SortRequest() {
    }

    public SortRequest(String field_name, Boolean ascending) {
        this.field_name = field_name;
        this.ascending = ascending;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }
}
