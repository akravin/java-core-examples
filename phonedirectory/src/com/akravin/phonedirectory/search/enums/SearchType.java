package com.akravin.phonedirectory.search.enums;

public enum SearchType {

    FIRST_NAME("Number of people whose first name is %s: %d"),
    LAST_NAME("Number of people whose last name is %s: %d"),
    NAME("Number of people whose name is %s: %d"),
    FULL_NAME("Number of people whose full name is %s: %d"),
    AREA_CODE("Number of people with area code (%s): %d"),
    PHONE("Number of people with phone number (%s): %d");

    private String pattern;

    SearchType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
