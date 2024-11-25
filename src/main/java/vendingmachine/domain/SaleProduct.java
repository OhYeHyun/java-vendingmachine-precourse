package vendingmachine.domain;

public class SaleProduct {
    private final Product product;
    private final int quantity;

    public SaleProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}