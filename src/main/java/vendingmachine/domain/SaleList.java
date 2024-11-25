package vendingmachine.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SaleList {
    private static final SaleList instance = new SaleList();
    private static final List<SaleProduct> saleList = new ArrayList<>();

    private SaleList() {}

    public static SaleList getInstance() {
        return instance;
    }

    public void addSaleProduct(SaleProduct product) {
        saleList.add(product);
    }

    public List<SaleProduct> getSaleList() {
        return Collections.unmodifiableList(saleList);
    }

    public void purchaseProduct(String productName) {
        SaleProduct product = findSaleProduct(productName);
        product.purchase();
    }

    private SaleProduct findSaleProduct(String productName) {
        return saleList.stream().filter(product -> Objects.equals(product.getProduct().getName(), productName)).findFirst().get();
    }
}
