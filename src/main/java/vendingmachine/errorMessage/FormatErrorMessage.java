package vendingmachine.errorMessage;

public enum FormatErrorMessage {
    MUST_BE_NUMERIC("%s은 숫자여야 합니다."),
    NOT_ALLOWED_PRODUCT_FORMAT("상품의 형식이 잘못되었습니다."),
    NOT_ALLOWED_ATTRIBUTE_FORMAT("상품 속성의 형식이 잘못되었습니다.");

    private static final String PREFIX = "[ERROR] ";
    private static final String AGAIN = " 다시 입력해주세요.";
    private final String message;

    FormatErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message + AGAIN;
    }

    public String getMessage(String label) {
        return String.format(PREFIX + message + AGAIN, label);
    }
}
