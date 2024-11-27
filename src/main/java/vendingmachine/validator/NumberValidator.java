package vendingmachine.validator;

import vendingmachine.errorMessage.FormatErrorMessage;

public class NumberValidator {

    public static void validateIsNumeric(String number, String label) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(FormatErrorMessage.MUST_BE_NUMERIC.getMessage(label));
        }
    }
}
