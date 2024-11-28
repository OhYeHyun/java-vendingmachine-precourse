package vendingmachine.initializer;

import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.parser.InputParser;
import vendingmachine.service.VendingMachineService;
import vendingmachine.view.VendingMachineInputView;
import vendingmachine.view.VendingMachineOutputView;

public class MachineInitializer {
    private final VendingMachineService machineService;

    public MachineInitializer(VendingMachineService machineService) {
        this.machineService = machineService;
    }

    public void processInitialize() {
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

    private void displayCoinHistory(Map<Coin, Integer> coinHistory) {
        VendingMachineOutputView.displayCoinHistory(coinHistory);
    }

    private int getAmount(boolean isFirst) {
        String amount = VendingMachineInputView.getAmount(isFirst);
        return InputParser.parseToNumber(amount);
    }
}
