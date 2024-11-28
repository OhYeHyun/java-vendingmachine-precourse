package vendingmachine.view;

import java.util.Map;
import vendingmachine.domain.Coin;

public class VendingMachineOutputView {

    public static void print(String text) {
        System.out.println(text);
    }

    public static void printLineSpace() {
        System.out.print(System.lineSeparator());
    }

    public static void printWithLineSpace(String text) {
        printLineSpace();
        print(text);
    }

    public static void instructionAmount(boolean isFirst) {
        if (isFirst) {
            print("자판기가 보유하고 있는 금액을 입력해 주세요.");
        }
        if (!isFirst) {
            printWithLineSpace("자판기가 보유하고 있는 금액을 입력해 주세요.");
        }
    }

    public static void displayCoinHistory(Map<Coin, Integer> coinHistory) {
        printWithLineSpace("자판기가 보유한 동전");
        coinHistory.forEach((coin, amount) -> {
            String format = String.format("%d원 - %d개", coin.getAmount(), amount);
            print(format);
        });
    }

    public static void instructionProduct() {
        printWithLineSpace("상품명과 가격, 수량을 입력해 주세요.");
    }

    public static void instructionMoney() {
        printWithLineSpace("투입 금액을 입력해 주세요.");
    }

    public static void instructionPurchase(int money) {
        String format = String.format("투입 금액: %d원", money);
        printWithLineSpace(format);
        print("구매할 상품명을 입력해 주세요.");
    }

    public static void displayReceiveHistory(int money, Map<Coin, Integer> receiveHistory) {
        String moneyFormat = String.format("투입 금액: %d원", money);
        printWithLineSpace(moneyFormat);
        print("잔돈");
        receiveHistory.forEach((coin, amount) -> {
            String receiveHistoryFormat = String.format("%d원 - %d개", coin.getAmount(), amount);
            print(receiveHistoryFormat);
        });
    }
}
