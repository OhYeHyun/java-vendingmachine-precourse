package vendingmachine.manager;

import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.parser.InputParser;
import vendingmachine.service.PurchaseService;
import vendingmachine.service.SaleListService;
import vendingmachine.service.VendingMachineService;
import vendingmachine.view.VendingMachineInputView;
import vendingmachine.view.VendingMachineOutputView;

public class PurchaseManager {
    private final SaleListService saleListService;
    private final VendingMachineService machineService;
    private PurchaseService purchaseService;

    public PurchaseManager(SaleListService saleListService, VendingMachineService machineService) {
        this.saleListService = saleListService;
        this.machineService = machineService;
    }

    public void processPurchase() {
        putMoney();
        purchaseProduct();
        displayReceiveHistory();
    }

    public void putMoney() {
        while (true) {
            try {
                int money = getMoney();
                purchaseService = new PurchaseService(money);
                return;
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    public void purchaseProduct() {
        while (saleListService.isRemainQuantity() && purchaseService.isExistToPurchaseProduct()) {
            try {
                int money = purchaseService.getMoney();
                String product = getPurchaseProducts(money);
                purchaseService.purchaseProduct(product);
                saleListService.purchaseProduct(product);
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    public void displayReceiveHistory() {
        int remainMoney = purchaseService.getMoney();
        Map<Coin, Integer> receiveHistory = machineService.receiveCoin(remainMoney);
        VendingMachineOutputView.displayReceiveHistory(remainMoney, receiveHistory);
    }

    private int getMoney() {
        String money = VendingMachineInputView.getMoney();
        return InputParser.parseToNumber(money);
    }

    private String getPurchaseProducts(int money) {
        VendingMachineOutputView.instructionPurchase(money);
        return VendingMachineInputView.getPurchaseProduct();
    }
}
