package br.com.b3.instrument.backend.data.json.repository;

import br.com.b3.instrument.backend.data.json.model.HistoricalPriceJSON;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
class HistoricalPriceJSONRepositoryIT {

    @Autowired
    private HistoricalPriceJSONRepository historicalPriceJSONRepository;

    @Test
    void givenPricesJsonFileWhenFindAllThenReturnAllHistoricalPrices() {

        List<HistoricalPriceJSON> historicalPrices = historicalPriceJSONRepository.findAll();
        Assertions.assertThat(historicalPrices)
                .hasSize(8)
                .extracting("symbol")
                .containsExactlyInAnyOrder(
                        "PETR4",
                        "VALE5",
                        "MGLU3",
                        "AMZN2",
                        "GOGL4",
                        "NBNK4",
                        "BHIA3",
                        "BNDE3"
                );

        historicalPrices.forEach(historicalPriceJSON -> {
            List<HistoricalPriceJSON.Serie> series =
                    collectPriceSeriesBySymbol(historicalPrices, historicalPriceJSON.getSymbol());

            Assertions.assertThat(series)
                    .extracting("price")
                    .hasSize(10)
                    .doesNotContainNull();
        });

    }

    private List<HistoricalPriceJSON.Serie> collectPriceSeriesBySymbol(List<HistoricalPriceJSON> historicalPrices, String symbol) {
        return historicalPrices.stream()
                .filter(historicalPriceJSON -> Objects.equals(historicalPriceJSON.getSymbol(), symbol))
                .flatMap(historicalPriceJSON -> Arrays.stream(historicalPriceJSON.getSeries()))
                .collect(Collectors.toList());
    }

}