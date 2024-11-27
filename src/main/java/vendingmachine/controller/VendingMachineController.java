package vendingmachine.controller;

import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.parser.InputParser;
import vendingmachine.service.PurchaseService;
import vendingmachine.service.SaleListService;
import vendingmachine.service.VendingMachineService;
import vendingmachine.view.VendingMachineInputView;
import vendingmachine.view.VendingMachineOutputView;

public class VendingMachineController {
   private final VendingMachineService machineService;
   private final SaleListService saleListService;
   private PurchaseService purchaseService;

    public VendingMachineController(VendingMachineService machineService, SaleListService saleListService) {
        this.machineService = machineService;
        this.saleListService = saleListService;
    }

    public void run() {
        processInitializeMachine();
        processAddProduct();
        processPutInputAmount();
        processPurchaseProduct();
        processDisplayReceiveHistory(purchaseService.getInputAmount());
    }

    private void processInitializeMachine() {
        boolean isFirst = true;
        while (true) {
            try {
                int amount = getAmount(isFirst);
                isFirst = false;
                machineService.generateCoin(amount);
                displayCoinHistory(machineService.getCoinHistory());
                return;
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    private int getAmount(boolean isFirst) {
        String amount = VendingMachineInputView.getAmount(isFirst);
        return InputParser.parseToNumber(amount);
    }

    private void displayCoinHistory(Map<Coin, Integer> coinHistory) {
        VendingMachineOutputView.displayCoinHistory(coinHistory);
    }

    private void processAddProduct() {
        while (true) {
            try {
                addProduct();
                return;
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    private String[] getProduct() {
        String products = VendingMachineInputView.getProducts();
        return InputParser.parseProducts(products);
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

    private void processPurchaseProduct() {
        while (saleListService.isRemainQuantity() && purchaseService.isExistToPurchaseProduct()) {
            purchaseProduct();
        }
    }

    private void purchaseProduct() {
        while (true) {
            try {
                int inputAmount = purchaseService.getInputAmount();
                String product = getPurchaseProducts(inputAmount);
                purchaseService.purchaseProduct(product);
                saleListService.purchaseProduct(product);
                return;
            } catch (IllegalArgumentException e) {
                VendingMachineOutputView.printWithLineSpace(e.getMessage());
            }
        }
    }

    private void processPutInputAmount() {
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

    private int getInputAmount() {
        String inputAmount = VendingMachineInputView.getInputAmount();
        return InputParser.parseToNumber(inputAmount);
    }

    private String getPurchaseProducts(int inputAmount) {
        VendingMachineOutputView.instructionPurchase(inputAmount);
        return VendingMachineInputView.getPurchaseProduct();
    }

    private void processDisplayReceiveHistory(int remainInputAmount) {
        Map<Coin, Integer> receiveHistory = machineService.receiveCoin(remainInputAmount);
        VendingMachineOutputView.displayReceiveHistory(remainInputAmount, receiveHistory);
    }
}
