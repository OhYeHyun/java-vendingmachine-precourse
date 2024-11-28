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
        putInputAmount();
        purchaseProduct();
        displayReceiveHistory();
    }

    public void putInputAmount() {
        while (true) {
            try {
                int inputAmount = getInputAmount();
                purchaseService = new PurchaseService(inputAmount);
                return;
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    public void purchaseProduct() {
        while (saleListService.isRemainQuantity() && purchaseService.isExistToPurchaseProduct()) {
            try {
                int inputAmount = purchaseService.getInputAmount();
                String product = getPurchaseProducts(inputAmount);
                purchaseService.purchaseProduct(product);
                saleListService.purchaseProduct(product);
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    public void displayReceiveHistory() {
        int remainInputAmount = purchaseService.getInputAmount();
        Map<Coin, Integer> receiveHistory = machineService.receiveCoin(remainInputAmount);
        VendingMachineOutputView.displayReceiveHistory(remainInputAmount, receiveHistory);
    }

    private int getInputAmount() {
        String inputAmount = VendingMachineInputView.getInputAmount();
        return InputParser.parseToNumber(inputAmount);
    }

    private String getPurchaseProducts(int inputAmount) {
        VendingMachineOutputView.instructionPurchase(inputAmount);
        return VendingMachineInputView.getPurchaseProduct();
    }
}
