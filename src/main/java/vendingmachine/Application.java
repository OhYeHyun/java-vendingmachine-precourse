package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.domain.RandomCoinGenerator;
import vendingmachine.domain.VendingMachine;
import vendingmachine.service.SaleListService;
import vendingmachine.service.VendingMachineService;

public class Application {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        RandomCoinGenerator generator = new RandomCoinGenerator();
        VendingMachineService vendingMachineService = new VendingMachineService(machine, generator);
        SaleListService saleListService = new SaleListService();

        VendingMachineController controller = new VendingMachineController(vendingMachineService, saleListService);

        controller.run();
    }
}
