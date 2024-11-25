package vendingmachine.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VendingMachine {
    private final CoinHistory coinHistory;

    public VendingMachine(CoinHistory coinHistory) {
        this.coinHistory = coinHistory;
    }

    public Map<Coin, Integer> receiveCoin(int amount) {
        Map<Coin, Integer> receiveHistory = new LinkedHashMap<>();
        Map<Coin, Integer> history = coinHistory.getCoinHistory();

        for (Entry<Coin, Integer> coin : history.entrySet()) {
            int coinAmount = coin.getKey().getAmount() * coin.getValue();

            if (coinAmount > 0) {
                if (coinAmount > amount) {
                    while (coinAmount > amount) {
                        coinAmount -= coin.getKey().getAmount();
                    }
                    int useCoin = coinAmount / coin.getKey().getAmount();
                    receiveHistory.put(coin.getKey(), coin.getValue());
                    amount -= coinAmount;

                    coinHistory.useCoin(coin.getKey(), useCoin);
                }
                if (coinAmount <= amount) {
                    receiveHistory.put(coin.getKey(), coin.getValue());
                    amount -= coinAmount;
                    coinHistory.useCoin(coin.getKey(), coin.getValue());
                }
            }
        }
        return receiveHistory;
    }
}
