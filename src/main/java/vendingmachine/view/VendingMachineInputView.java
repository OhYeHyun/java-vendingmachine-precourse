package vendingmachine.view;

import java.util.Scanner;
import vendingmachine.validator.AmountFormatValidator;
import vendingmachine.validator.ProductFormatValidator;

public class VendingMachineInputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static String getInput() {
        return scanner.nextLine();
    }

    public static String getAmount(boolean isFirst) {
        while (true) {
            VendingMachineOutputView.instructionAmount(isFirst);
            isFirst = false;
            String amount = getInput();
            try {
                AmountFormatValidator.validate(amount);
                return amount;
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    public static String getMoney() {
        while (true) {
            VendingMachineOutputView.instructionMoney();
            String amount = getInput();
            try {
                AmountFormatValidator.validate(amount);
                return amount;
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    public static String getProducts() {
        while (true) {
            VendingMachineOutputView.instructionProduct();
            String products = getInput();
            try {
                ProductFormatValidator.validate(products);
                return products;
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    public static String getPurchaseProduct() {
        return getInput();
    }
}
