package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomCoinGeneratorTest {

    @Test
    @DisplayName("보유 금액으로 동전을 랜덤하게 생성하는지 확인")
    void 랜덤_생성_테스트() {
        CoinHistory coinHistory = CoinHistory.getInstance();

        RandomCoinGenerator generator = new RandomCoinGenerator(coinHistory);
        generator.generateCoin(500);

        Map<Coin, Integer> result = coinHistory.getCoinHistory();
        assertThat(result.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getAmount() * entry.getValue()).sum())
                .isEqualTo(500);
    }
}