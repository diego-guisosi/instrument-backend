package br.com.b3.instrument.backend.controller.converter;

import br.com.b3.instrument.backend.controller.model.HistoricalPriceResponse;
import br.com.b3.instrument.backend.data.jpa.model.HistoricalPriceJPA;
import br.com.b3.instrument.backend.data.jpa.model.InstrumentJPA;
import br.com.b3.instrument.backend.data.json.model.HistoricalPriceJSON;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


class HistoricalPriceConverterTest {

    @Test
    void givenNullHistoricalPriceJPAListWhenConvertThenThrowsNullPointerException(){
        List<HistoricalPriceJPA> prices = null;
        Assertions.assertThatThrownBy(() -> HistoricalPriceConverter.convert(prices))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(HistoricalPriceConverter.ERROR_MESSAGE_MANDATORY_PRICE);
    }

    @Test
    void givenEmptyHistoricalPriceJPAListWhenConvertThenReturnEmptyHistoricalPriceResponse(){
        HistoricalPriceJSON price = new HistoricalPriceJSON();

        HistoricalPriceResponse historicalPriceResponse = HistoricalPriceConverter.convert(price);

        Assertions.assertThat(historicalPriceResponse)
                .extracting("symbol")
                .isNull();

        Assertions.assertThat(historicalPriceResponse.getSeries())
                .isEmpty();
    }


    @Test
    void givenCompleteHistoricalPriceJPAListWhenConvertThenReturnCompleteHistoricalPriceResponse(){

        InstrumentJPA instrumentJPA = new InstrumentJPA();
        instrumentJPA.setId(1L);
        instrumentJPA.setSymbol("PETR4");
        instrumentJPA.setMaturityDate(LocalDate.of(2021,12,31));

        HistoricalPriceJPA firstJPAPrice = createJPASerie(instrumentJPA, LocalDate.of(2020, 1, 12), 11.0);
        HistoricalPriceJPA secondJPAPrice = createJPASerie(instrumentJPA, LocalDate.of(2020, 1, 13), 15.0);

        HistoricalPriceResponse historicalPriceResponse =
                HistoricalPriceConverter.convert(Arrays.asList(firstJPAPrice, secondJPAPrice));

        Assertions.assertThat(historicalPriceResponse.getSymbol()).isEqualTo(instrumentJPA.getSymbol());
        Assertions.assertThat(historicalPriceResponse.getSeries())
                .extracting("date", "price")
                .contains(
                        Assertions.tuple(firstJPAPrice.getDate(), firstJPAPrice.getPrice()),
                        Assertions.tuple(secondJPAPrice.getDate(), secondJPAPrice.getPrice())
                );
    }

    private HistoricalPriceJPA createJPASerie(InstrumentJPA instrumentJPA, LocalDate date, double price) {
        HistoricalPriceJPA historicalPriceJPA = new HistoricalPriceJPA();
        historicalPriceJPA.setInstrument(instrumentJPA);
        historicalPriceJPA.setDate(date);
        historicalPriceJPA.setPrice(price);
        return historicalPriceJPA;
    }

    @Test
    void givenNullHistoricalPriceJSONWhenConvertThenThrowsNullPointerException(){
        HistoricalPriceJSON price = null;
        Assertions.assertThatThrownBy(() -> HistoricalPriceConverter.convert(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(HistoricalPriceConverter.ERROR_MESSAGE_MANDATORY_PRICE);
    }

    @Test
    void givenEmptyHistoricalPriceJSONWhenConvertThenReturnEmptyHistoricalPriceResponse(){
        HistoricalPriceJSON price = new HistoricalPriceJSON();

        HistoricalPriceResponse historicalPriceResponse = HistoricalPriceConverter.convert(price);

        Assertions.assertThat(historicalPriceResponse)
                .extracting("symbol")
                .isNull();

        Assertions.assertThat(historicalPriceResponse.getSeries())
                .isEmpty();
    }


    @Test
    void givenCompleteHistoricalPriceJSONWhenConvertThenReturnCompleteHistoricalPriceResponse(){

        HistoricalPriceJSON.Serie firstJSONPrice = createJSONSerie(LocalDate.of(2020, 1, 12), 11.0);
        HistoricalPriceJSON.Serie secondJSONPrice = createJSONSerie(LocalDate.of(2020, 1, 13), 15.0);

        HistoricalPriceJSON price = new HistoricalPriceJSON();
        price.setSymbol("PETR4");
        price.setSeries(new HistoricalPriceJSON.Serie[] {firstJSONPrice, secondJSONPrice});

        HistoricalPriceResponse historicalPriceResponse = HistoricalPriceConverter.convert(price);

        Assertions.assertThat(historicalPriceResponse.getSymbol()).isEqualTo(price.getSymbol());
        Assertions.assertThat(historicalPriceResponse.getSeries())
                .extracting("date", "price")
                .contains(
                        Assertions.tuple(firstJSONPrice.getDate(), firstJSONPrice.getPrice()),
                        Assertions.tuple(secondJSONPrice.getDate(), secondJSONPrice.getPrice())
                );
    }

    private HistoricalPriceJSON.Serie createJSONSerie(LocalDate date, double price) {
        HistoricalPriceJSON.Serie serie = new HistoricalPriceJSON.Serie();
        serie.setDate(date);
        serie.setPrice(price);
        return serie;
    }

}