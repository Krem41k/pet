package com.example.pet.utils;

public enum Operation {
    LESS ("lessThan"),
    MORE ("moreThan"),
    EQUAL ("equal");
    private final String title;

    Operation(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
