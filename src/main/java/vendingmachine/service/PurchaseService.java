package vendingmachine.service;

import java.util.NoSuchElementException;
import vendingmachine.domain.SaleList;
import vendingmachine.errorMessage.PurchaseErrorMessage;

public class PurchaseService {
    private final SaleList saleList;
    private int inputAmount;

    public PurchaseService(int inputAmount) {
        validate(inputAmount);
        this.saleList = SaleList.getInstance();
        this.inputAmount = inputAmount;
    }

    public void purchaseProduct(String productName) {
        validateProductName(productName);
        int price = saleList.findSaleProduct(productName).getProduct().getPrice();
        validatePrice(price);
        int quantity = saleList.findSaleProduct(productName).getQuantity();
        validateQuantity(quantity);

        inputAmount -= price;
    }

    public boolean isExistToPurchaseProduct() {
        int minimumPrice = saleList.getSaleList().stream().mapToInt(product -> product.getProduct().getPrice()).min().getAsInt();
        return inputAmount >= minimumPrice;
    }

    public int getInputAmount() {
        return inputAmount;
    }

    private void validateProductName(String productName) {
        try {
            saleList.findSaleProduct(productName);
        } catch(NoSuchElementException e) {
            throw new IllegalArgumentException(PurchaseErrorMessage.NOT_FOUND_PRODUCT.getMessage());
        }
    }

    private void validatePrice(int price) {
        if (inputAmount < price) {
            throw new IllegalArgumentException(PurchaseErrorMessage.NOT_ENOUGH_MONEY.getMessage());
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity == 0) {
            throw new IllegalArgumentException(PurchaseErrorMessage.QUANTITY_SHORTAGE.getMessage());
        }
    }

    private void validate(int inputAmount) {
        if (inputAmount < 0) {
            throw new IllegalArgumentException(PurchaseErrorMessage.MUST_NOT_BE_NEGATIVE_MONEY.getMessage());
        }
    }
}
