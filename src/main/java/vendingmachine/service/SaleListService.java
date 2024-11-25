package vendingmachine.service;

import vendingmachine.domain.SaleList;

public class SaleListService {
    private final SaleList saleList;

    public SaleListService() {
        this.saleList = SaleList.getInstance();
    }

    public void purchaseProduct(String productName) {
        saleList.purchaseProduct(productName);
    }

    public boolean isRemainQuantity() {
        int remainQuantity = saleList.getSaleList().stream().mapToInt(product -> product.getQuantity()).sum();
        return remainQuantity > 0;
    }
}
