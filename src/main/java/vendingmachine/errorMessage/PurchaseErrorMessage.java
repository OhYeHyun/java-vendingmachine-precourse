package vendingmachine.errorMessage;

public enum PurchaseErrorMessage {
    NOT_FOUND_PRODUCT("존재하지 않는 상품입니다."),
    NOT_ENOUGH_MONEY("잔액이 부족합니다."),
    QUANTITY_SHORTAGE("수량이 부족합니다.");

    private static final String PREFIX = "[ERROR] ";
    private static final String AGAIN = "다시 입력해주세요.";
    private final String message;

    PurchaseErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message + AGAIN;
    }
}
