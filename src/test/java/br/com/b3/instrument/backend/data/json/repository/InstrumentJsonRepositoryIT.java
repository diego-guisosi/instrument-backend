package br.com.b3.instrument.backend.data.json.repository;

import br.com.b3.instrument.backend.data.json.model.InstrumentJSON;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class InstrumentJsonRepositoryIT {

    @Autowired
    private InstrumentJsonRepository instrumentJsonRepository;

    @Test
    void givenInstrumentsJsonFileWhenFindAllThenReturnAllInstruments() {

        List<InstrumentJSON> instruments = instrumentJsonRepository.findAll();
        Assertions.assertThat(instruments)
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

    }
}