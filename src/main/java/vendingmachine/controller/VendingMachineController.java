package vendingmachine.controller;

import vendingmachine.initializer.MachineInitializer;
import vendingmachine.manager.ProductManager;
import vendingmachine.manager.PurchaseManager;

public class VendingMachineController {
   private final MachineInitializer initializer;
   private final ProductManager productManager;
   private final PurchaseManager purchaseManager;

    public VendingMachineController(MachineInitializer initializer, ProductManager productManager, PurchaseManager purchaseManager) {
        this.initializer = initializer;
        this.productManager = productManager;
        this.purchaseManager = purchaseManager;
    }

    public void run() {
        initializer.processInitialize();
        productManager.processAddProducts();
        purchaseManager.processPurchase();
    }
}
