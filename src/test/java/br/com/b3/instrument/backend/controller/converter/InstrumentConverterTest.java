package br.com.b3.instrument.backend.controller.converter;

import br.com.b3.instrument.backend.controller.model.InstrumentResponse;
import br.com.b3.instrument.backend.data.jpa.model.InstrumentJPA;
import br.com.b3.instrument.backend.data.json.model.InstrumentJSON;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class InstrumentConverterTest {

    @Test
    void givenNullInstrumentJPAWhenConvertThenThrowsNullPointerException(){
        InstrumentJPA instrument = null;
        Assertions.assertThatThrownBy(() -> InstrumentConverter.convert(instrument))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InstrumentConverter.ERROR_MESSAGE_MANDATORY_INSTRUMENT);
    }

    @Test
    void givenEmptyInstrumentJPAWhenConvertThenReturnEmptyInstrumentResponse(){
        InstrumentJPA instrument = new InstrumentJPA();

        InstrumentResponse instrumentResponse = InstrumentConverter.convert(instrument);

        Assertions.assertThat(instrumentResponse)
                .extracting("symbol", "maturityDate")
                .containsOnlyNulls();
    }

    @Test
    void givenInstrumentJPAWithIdWhenConvertThenReturnEmptyInstrumentResponse(){
        InstrumentJPA instrument = new InstrumentJPA();
        instrument.setId(1L);

        InstrumentResponse instrumentResponse = InstrumentConverter.convert(instrument);

        Assertions.assertThat(instrumentResponse)
                .extracting("symbol", "maturityDate")
                .containsOnlyNulls();
    }

    @Test
    void givenCompleteInstrumentJPAWhenConvertThenReturnCompleteInstrumentResponse(){
        InstrumentJPA instrument = new InstrumentJPA();
        instrument.setId(1L);
        instrument.setSymbol("PETR4");
        instrument.setMaturityDate(LocalDate.of(2020,2, 1));

        InstrumentResponse instrumentResponse = InstrumentConverter.convert(instrument);

        Assertions.assertThat(instrumentResponse)
                .extracting("symbol", "maturityDate")
                .contains(instrument.getSymbol(), instrument.getMaturityDate());
    }

    @Test
    void givenNullInstrumentJSONWhenConvertThenThrowsNullPointerException(){
        InstrumentJSON instrument = null;
        Assertions.assertThatThrownBy(() -> InstrumentConverter.convert(instrument))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InstrumentConverter.ERROR_MESSAGE_MANDATORY_INSTRUMENT);
    }

    @Test
    void givenEmptyInstrumentJSONWhenConvertThenReturnEmptyInstrumentResponse(){
        InstrumentJSON instrument = new InstrumentJSON();

        InstrumentResponse instrumentResponse = InstrumentConverter.convert(instrument);

        Assertions.assertThat(instrumentResponse)
                .extracting("symbol", "maturityDate")
                .containsOnlyNulls();
    }

    @Test
    void givenInstrumentJSONWithIdWhenConvertThenReturnEmptyInstrumentResponse(){
        InstrumentJSON instrument = new InstrumentJSON();
        instrument.setId(1L);

        InstrumentResponse instrumentResponse = InstrumentConverter.convert(instrument);

        Assertions.assertThat(instrumentResponse)
                .extracting("symbol", "maturityDate")
                .containsOnlyNulls();
    }

    @Test
    void givenCompleteInstrumentJSONWhenConvertThenReturnCompleteInstrumentResponse(){
        InstrumentJSON instrument = new InstrumentJSON();
        instrument.setId(1L);
        instrument.setSymbol("PETR4");
        instrument.setMaturityDate(LocalDate.of(2020,2, 1));

        InstrumentResponse instrumentResponse = InstrumentConverter.convert(instrument);

        Assertions.assertThat(instrumentResponse)
                .extracting("symbol", "maturityDate")
                .contains(instrument.getSymbol(), instrument.getMaturityDate());
    }

}