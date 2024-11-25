package vendingmachine.errorMessage;

public enum SaleListErrorMessage {
    BELOW_MINIMUM_PRICE("가격이 100원 이상이어야 합니다."),
    MUST_BE_10_UNITS_PRICE("가격이 10원 단위이어야 합니다."),
    MUST_BE_EXIST_QUANTITY("수량이 1 이상이어야 합니다."),
    ALREADY_EXIST_PRODUCT("이미 존재하는 상품입니다.");

    private static final String PREFIX = "[ERROR] ";
    private static final String AGAIN = "다시 입력해주세요.";
    private final String message;

    SaleListErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message + AGAIN;
    }
}
