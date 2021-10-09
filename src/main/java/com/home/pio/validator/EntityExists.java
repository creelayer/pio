package com.home.pio.validator;

public interface EntityExists {
    boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}
