package vendingmachine.manager;

import vendingmachine.parser.InputParser;
import vendingmachine.service.SaleListService;
import vendingmachine.view.VendingMachineInputView;
import vendingmachine.view.VendingMachineOutputView;

public class ProductManager {
    private final SaleListService saleListService;

    public ProductManager(SaleListService saleListService) {
        this.saleListService = saleListService;
    }

    public void processAddProducts() {
        while (true) {
            try {
                addProduct();
                return;
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    private void addProduct() {
        for (String product : getProduct()) {
            String name = InputParser.parseNameByProduct(product);
            int price = InputParser.parsePriceByProduct(product);
            int quantity = InputParser.parseQuantityByProduct(product);

            saleListService.validateProduct(name, price, quantity);
        }
        saleListService.addProduct();
    }

    private String[] getProduct() {
        String products = VendingMachineInputView.getProducts();
        return InputParser.parseProducts(products);
    }
}
