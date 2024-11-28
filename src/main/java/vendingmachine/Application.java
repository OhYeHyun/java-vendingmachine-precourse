package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.domain.RandomCoinGenerator;
import vendingmachine.domain.VendingMachine;
import vendingmachine.initializer.MachineInitializer;
import vendingmachine.manager.ProductManager;
import vendingmachine.manager.PurchaseManager;
import vendingmachine.service.SaleListService;
import vendingmachine.service.VendingMachineService;

public class Application {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        RandomCoinGenerator generator = new RandomCoinGenerator();
        VendingMachineService vendingMachineService = new VendingMachineService(machine, generator);
        SaleListService saleListService = new SaleListService();

        MachineInitializer initializer = new MachineInitializer(vendingMachineService);
        ProductManager productManager = new ProductManager(saleListService);
        PurchaseManager purchaseManager = new PurchaseManager(saleListService, vendingMachineService);

        VendingMachineController controller = new VendingMachineController(initializer, productManager, purchaseManager);

        controller.run();
    }
}
