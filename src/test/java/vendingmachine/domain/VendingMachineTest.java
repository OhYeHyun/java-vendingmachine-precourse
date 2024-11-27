package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VendingMachineTest {

    @Test
    @DisplayName("잔돈 계산이 올바르게 나오는지 확인")
    void 잔돈_테스트() {
        CoinHistory coinHistory = CoinHistory.getInstance();
        coinHistory.putCoin(Coin.COIN_100);
        coinHistory.putCoin(Coin.COIN_100);
        coinHistory.putCoin(Coin.COIN_100);
        coinHistory.putCoin(Coin.COIN_100);
        coinHistory.putCoin(Coin.COIN_50);

        VendingMachine machine = new VendingMachine();

        Map<Coin, Integer> result = machine.receiveCoin(350);

        assertThat(result.get(Coin.COIN_100)).isEqualTo(3);
        assertThat(result.get(Coin.COIN_50)).isEqualTo(1);
    }
}