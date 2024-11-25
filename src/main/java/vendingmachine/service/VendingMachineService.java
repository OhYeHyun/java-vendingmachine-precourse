package vendingmachine.service;

import vendingmachine.domain.RandomCoinGenerator;
import vendingmachine.domain.VendingMachine;
import vendingmachine.errorMessage.VendingMachineErrorMessage;

public class VendingMachineService {
    private final VendingMachine machine;
    private final RandomCoinGenerator generator;

    public VendingMachineService(VendingMachine machine, RandomCoinGenerator generator) {
        this.machine = machine;
        this.generator = generator;
    }

    public void generateCoin(int amount) {
        validateAmount(amount);
        generator.generateCoin(amount);
    }

    public void receiveCoin(int amount) {
        machine.receiveCoin(amount);
    }

    public void displayCoinHistory() {
        machine.getCoinHistory().forEach((coin, amount) -> {
            String format = String.format("%d원 - %d개", coin.getAmount(), amount);
            System.out.print(format);
        });
    }

    private void validateAmount(int amount) {
        if (amount % 10 != 0) {
            throw new IllegalArgumentException(VendingMachineErrorMessage.MUST_BE_10_UNITS_PRICE.getMessage());
        }
    }
}
