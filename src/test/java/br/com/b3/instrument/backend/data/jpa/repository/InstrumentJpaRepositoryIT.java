package br.com.b3.instrument.backend.data.jpa.repository;

import br.com.b3.instrument.backend.data.jpa.model.InstrumentJPA;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestPropertySource(properties = "spring.flyway.enabled=false")
class InstrumentJpaRepositoryIT {

    @Autowired
    private InstrumentJpaRepository instrumentJpaRepository;

    @BeforeEach
    void setup(){
        instrumentJpaRepository.deleteAll();
    }

    @Test
    void givenInstrumentsWithDifferentMaturityDatesWhenFindAllThenReturnInstrumentsSortedByMaturityDate(){
        InstrumentJPA petr4 = setupInstrument(1L, "PETR4", LocalDate.of(2020, 2, 1));
        InstrumentJPA vale5 = setupInstrument(2L, "VALE5", LocalDate.of(2020, 1, 1));
        InstrumentJPA nbnk4 = setupInstrument(3L, "NBNK4", LocalDate.of(2020, 11, 11));
        InstrumentJPA mglu5 = setupInstrument(4L, "MGLU5", LocalDate.of(2021, 1, 1));
        InstrumentJPA bnde3 = setupInstrument(5L, "BNDE3", LocalDate.of(2020, 5, 5));

        List<InstrumentJPA> instrumentsSortedByMaturityDate = instrumentJpaRepository.findAllOrderByMaturityDateAsc();

        Assertions.assertThat(instrumentsSortedByMaturityDate)
                .containsExactly(vale5, petr4, bnde3, nbnk4, mglu5);
    }

    private InstrumentJPA setupInstrument(long id, String symbol, LocalDate maturityDate) {
        InstrumentJPA instrument = createInstrument(id, symbol, maturityDate);
        return instrumentJpaRepository.save(instrument);
    }

    private InstrumentJPA createInstrument(long id, String symbol, LocalDate maturityDate) {
        InstrumentJPA instrument = new InstrumentJPA();
        instrument.setId(id);
        instrument.setSymbol(symbol);
        instrument.setMaturityDate(maturityDate);
        return instrument;
    }

}