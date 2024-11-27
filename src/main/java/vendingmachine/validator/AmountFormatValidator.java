package vendingmachine.validator;

public class AmountFormatValidator {
    private static final String AMOUNT_LABEL = "금액";

    private AmountFormatValidator() {}

    public static void validate(String amount) {
        NumberValidator.validateIsNumeric(amount, AMOUNT_LABEL);
    }
}
