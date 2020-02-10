package br.com.b3.instrument.backend.data.jpa.repository;

import br.com.b3.instrument.backend.data.jpa.model.HistoricalPriceJPA;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestPropertySource(properties = "spring.flyway.enabled=false")
@Sql(scripts = {"/sql/delete_all_data.sql", "/sql/historicalPriceRepository/insert_petr4_and_vale5_historical_prices.sql"})
class HistoricalPriceJPARepositoryIT {

    @Autowired
    private HistoricalPriceJPARepository historicalPriceJPARepository;

    @Test
    void givenPetr4AndVale5PricesWhenFindByPetr4AndYear2020ThenReturnOnly2020Petr4Prices(){

        LocalDate minimumDate = LocalDate.of(2020, 1, 1);
        LocalDate maximumDate = LocalDate.of(2020, 12, 31);

        List<HistoricalPriceJPA> petr4Prices =
                historicalPriceJPARepository.findBySymbolAndDateRange("PETR4", minimumDate, maximumDate);

        Assertions.assertThat(petr4Prices)
                .extracting("instrument")
                .extracting("symbol")
                .containsOnly("PETR4")
                .hasSize(1);

        Assertions.assertThat(petr4Prices)
                .extracting("price")
                .doesNotContainNull();

    }

    @Test
    void givenPetr4AndVale5PricesWhenFindByVale5AndYear2019ThenReturnOnly2019Vale5Prices(){

        LocalDate minimumDate = LocalDate.of(2019, 1, 1);
        LocalDate maximumDate = LocalDate.of(2019, 12, 31);

        List<HistoricalPriceJPA> vale5Prices =
                historicalPriceJPARepository.findBySymbolAndDateRange("VALE5", minimumDate, maximumDate);

        Assertions.assertThat(vale5Prices)
                .extracting("instrument")
                .extracting("symbol")
                .containsOnly("VALE5")
                .hasSize(8);

        Assertions.assertThat(vale5Prices)
                .extracting("price")
                .doesNotContainNull();

    }

}