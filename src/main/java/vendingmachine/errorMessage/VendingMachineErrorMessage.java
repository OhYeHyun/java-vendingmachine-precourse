package vendingmachine.errorMessage;

public enum VendingMachineErrorMessage {
    MUST_BE_10_UNITS_PRICE("가격이 10원 단위이어야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private static final String AGAIN = "다시 입력해주세요.";
    private final String message;

    VendingMachineErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message + AGAIN;
    }
}
