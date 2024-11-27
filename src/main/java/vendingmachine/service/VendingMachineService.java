package vendingmachine.service;

import java.util.Map;
import vendingmachine.domain.Coin;
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

    public Map<Coin, Integer> receiveCoin(int amount) {
        return machine.receiveCoin(amount);
    }

    public Map<Coin, Integer> getCoinHistory() {
        return machine.getCoinHistory();
    }

    private void validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(VendingMachineErrorMessage.MUST_NOT_BE_NEGATIVE_PRICE.getMessage());
        }

        if (amount % 10 != 0) {
            throw new IllegalArgumentException(VendingMachineErrorMessage.MUST_BE_10_UNITS_PRICE.getMessage());
        }
    }
}
