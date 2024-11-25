package vendingmachine.domain;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInList;

import java.util.Arrays;
import java.util.List;

public class RandomCoinGenerator {
    private static final Coin[] COINS_INDEX = {Coin.COIN_500, Coin.COIN_100, Coin.COIN_50, Coin.COIN_10};
    private static final List<Integer> INDEX = Arrays.asList(0, 1, 2, 3);
    private final CoinHistory coinHistory;

    public RandomCoinGenerator(CoinHistory coinHistory) {
        this.coinHistory = CoinHistory.getInstance();
    }

    public void generateCoin(int amount) {
        while (amount > 10) {
            Coin randomCoin = COINS_INDEX[pickNumberInList(INDEX)];
            if (amount >= randomCoin.getAmount()) {
                amount -= randomCoin.getAmount();
                coinHistory.putCoin(randomCoin);
            }
        }
        if (amount == 10) {
            coinHistory.putCoin(Coin.COIN_10);
        }
    }
}
