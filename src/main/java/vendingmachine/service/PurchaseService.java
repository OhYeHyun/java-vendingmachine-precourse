package vendingmachine.service;

import java.util.NoSuchElementException;
import vendingmachine.domain.SaleList;
import vendingmachine.errorMessage.PurchaseErrorMessage;

public class PurchaseService {
    private final SaleList saleList;
    private int money;

    public PurchaseService(int money) {
        validate(money);
        this.saleList = SaleList.getInstance();
        this.money = money;
    }

    public void purchaseProduct(String productName) {
        validateProductName(productName);
        int price = saleList.findSaleProduct(productName).getProduct().getPrice();
        validatePrice(price);
        int quantity = saleList.findSaleProduct(productName).getQuantity();
        validateQuantity(quantity);

        money -= price;
    }

    public boolean isExistToPurchaseProduct() {
        int minimumPrice = saleList.getSaleList().stream().mapToInt(product -> product.getProduct().getPrice()).min().getAsInt();
        return money >= minimumPrice;
    }

    public int getMoney() {
        return money;
    }

    private void validateProductName(String productName) {
        try {
            saleList.findSaleProduct(productName);
        } catch(NoSuchElementException e) {
            throw new IllegalArgumentException(PurchaseErrorMessage.NOT_FOUND_PRODUCT.getMessage());
        }
    }

    private void validatePrice(int price) {
        if (money < price) {
            throw new IllegalArgumentException(PurchaseErrorMessage.NOT_ENOUGH_MONEY.getMessage());
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity == 0) {
            throw new IllegalArgumentException(PurchaseErrorMessage.QUANTITY_SHORTAGE.getMessage());
        }
    }

    private void validate(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(PurchaseErrorMessage.MUST_NOT_BE_NEGATIVE_MONEY.getMessage());
        }
    }
}
