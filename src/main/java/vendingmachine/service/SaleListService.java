package vendingmachine.service;

import java.util.NoSuchElementException;
import vendingmachine.domain.Product;
import vendingmachine.domain.SaleList;
import vendingmachine.domain.SaleProduct;
import vendingmachine.errorMessage.SaleListErrorMessage;

public class SaleListService {
    private final SaleList saleList;

    public SaleListService() {
        this.saleList = SaleList.getInstance();
    }

    public void addProduct(String productName, int price, int quantity) {
        validateProductName(productName);
        validatePrice(price);
        validateQuantity(quantity);

        saleList.addSaleProduct(new SaleProduct(new Product(productName, price), quantity));
    }

    public void purchaseProduct(String productName) {
        saleList.purchaseProduct(productName);
    }

    public boolean isRemainQuantity() {
        int remainQuantity = saleList.getSaleList().stream().mapToInt(product -> product.getQuantity()).sum();
        return remainQuantity > 0;
    }

    private void validatePrice(int price) {
        if (price < 100) {
            throw new IllegalArgumentException(SaleListErrorMessage.BELOW_MINIMUM_PRICE.getMessage());
        }

        if (price % 10 != 0) {
            throw new IllegalArgumentException(SaleListErrorMessage.MUST_BE_10_UNITS_PRICE.getMessage());
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(SaleListErrorMessage.MUST_BE_EXIST_QUANTITY.getMessage());
        }
    }

    private void validateProductName(String productName) {
        try {
            saleList.findSaleProduct(productName);
            throw new IllegalArgumentException(SaleListErrorMessage.ALREADY_EXIST_PRODUCT.getMessage());
        } catch (NoSuchElementException ignored) {
        }
    }
}
