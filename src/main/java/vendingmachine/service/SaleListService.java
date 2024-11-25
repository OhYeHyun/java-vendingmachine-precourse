package vendingmachine.service;

import vendingmachine.domain.SaleList;

public class SaleListService {
    private final SaleList saleList;

    public SaleListService(SaleList saleList) {
        this.saleList = SaleList.getInstance();
    }

    public void purchaseProduct(String productName) {
        saleList.purchaseProduct(productName);
    }
}
