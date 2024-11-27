package vendingmachine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import vendingmachine.domain.Product;
import vendingmachine.domain.SaleList;
import vendingmachine.domain.SaleProduct;
import vendingmachine.errorMessage.SaleListErrorMessage;

public class SaleListService {
    private final SaleList saleList;
    private final List<SaleProduct> productToAdd = new ArrayList<>();

    public SaleListService() {
        this.saleList = SaleList.getInstance();
    }

    public void validateProduct(String productName, int price, int quantity) {
        validateProductName(productName);
        validatePrice(price);
        validateQuantity(quantity);

        productToAdd.add(new SaleProduct(new Product(productName, price), quantity));
    }

    public void addProduct() {
        productToAdd.forEach(saleList::addSaleProduct);
        productToAdd.clear();
    }

    public void purchaseProduct(String productName) {
        saleList.purchaseProduct(productName);
    }

    public boolean isRemainQuantity() {
        int remainQuantity = saleList.getSaleList().stream().mapToInt(SaleProduct::getQuantity).sum();
        return remainQuantity > 0;
    }

    private void validatePrice(int price) {
        if (price < 100) {
            productToAdd.clear();
            throw new IllegalArgumentException(SaleListErrorMessage.BELOW_MINIMUM_PRICE.getMessage());
        }

        if (price % 10 != 0) {
            productToAdd.clear();
            throw new IllegalArgumentException(SaleListErrorMessage.MUST_BE_10_UNITS_PRICE.getMessage());
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            productToAdd.clear();
            throw new IllegalArgumentException(SaleListErrorMessage.MUST_BE_EXIST_QUANTITY.getMessage());
        }
    }

    private void validateProductName(String productName) {
        try {
            productToAdd.stream().filter(product -> Objects.equals(product.getProduct().getName(), productName)).findFirst().get();
            productToAdd.clear();
            throw new IllegalArgumentException(SaleListErrorMessage.ALREADY_EXIST_PRODUCT.getMessage());
        } catch (NoSuchElementException ignored) {
        }
    }
}
