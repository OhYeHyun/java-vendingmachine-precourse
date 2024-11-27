package vendingmachine.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VendingMachine {
    private final CoinHistory coinHistory;
    private final Map<Coin, Integer> receiveHistory = new LinkedHashMap<>();

    public VendingMachine() {
        this.coinHistory = CoinHistory.getInstance();
    }

    public void receiveCoin(int amount) {
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
    }

    public Map<Coin, Integer> getCoinHistory() {
        return Collections.unmodifiableMap(coinHistory.getCoinHistory());
    }

    public Map<Coin, Integer> getReceiveHistory() {
        return Collections.unmodifiableMap(receiveHistory);
    }
}
