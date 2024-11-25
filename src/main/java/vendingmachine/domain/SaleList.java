package vendingmachine.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
