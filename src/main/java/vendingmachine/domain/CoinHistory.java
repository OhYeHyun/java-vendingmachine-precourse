package vendingmachine.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CoinHistory {
    private static final Map<Coin, Integer> coinHistory = new LinkedHashMap<>();
    public static final CoinHistory instance = new CoinHistory();

    private CoinHistory() {
        initialize();
    }

    public static CoinHistory getInstance() {
        return instance;
    }

    private void initialize() {
        coinHistory.put(Coin.COIN_500, 0);
        coinHistory.put(Coin.COIN_100, 0);
        coinHistory.put(Coin.COIN_50, 0);
        coinHistory.put(Coin.COIN_10, 0);
    }

    public void setCoin(Coin coin, int amount) {
        coinHistory.put(coin, amount);
    }

    public void useCoin(Coin coin, int amount) {
        coinHistory.compute(coin, (k, curAmount) -> curAmount - amount);
    }

    public Map<Coin, Integer> getCoinHistory() {
        return Collections.unmodifiableMap(coinHistory);
    }
}
