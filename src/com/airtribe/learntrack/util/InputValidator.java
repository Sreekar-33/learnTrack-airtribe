package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {

    public static void validateNotEmpty(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static int parsePositiveInt(String value, String fieldName) throws InvalidInputException {
        try {
            int result = Integer.parseInt(value.trim());
            if (result <= 0) {
                throw new InvalidInputException(fieldName + " must be a positive number.");
            }
            return result;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a valid number.");
        }
    }

    public static int parseMenuOption(String value) throws InvalidInputException {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please enter a valid menu option (number).");
        }
    }
}
