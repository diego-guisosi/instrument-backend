package br.com.b3.instrument.backend.service;

import br.com.b3.instrument.backend.data.jpa.model.HistoricalPriceJPA;
import br.com.b3.instrument.backend.data.jpa.model.InstrumentJPA;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

class HistoricalPriceServiceTest {

    private HistoricalPriceService historicalPriceService = new HistoricalPriceService();

    @Test
    void givenNullWhenFindHighestAndMostRecentThenThrowsNullPointerException(){
        Assertions.assertThatThrownBy(() -> historicalPriceService.findHighestAndMostRecent(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenEmptyWhenFindHighestAndMostRecentThenReturnEmptyHistoricalPriceJPA(){
        HistoricalPriceJPA historicalPrice = historicalPriceService.findHighestAndMostRecent(Collections.emptyList());
        Assertions.assertThat(historicalPrice)
                .extracting("instrument", "date", "price")
                .containsOnlyNulls();
    }

    @Test
    void givenOneHistoricalPriceJPAWhenFindHighestAndMostRecentThenReturnHistoricalPriceJPA(){
        HistoricalPriceJPA historicalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 1, 1));

        HistoricalPriceJPA highestAndMostRecentHistoricalPrice =
                historicalPriceService.findHighestAndMostRecent(Collections.singletonList(historicalPrice));

        Assertions.assertThat(highestAndMostRecentHistoricalPrice)
                .extracting("instrument", "date", "price")
                .contains(
                        historicalPrice.getInstrument(),
                        historicalPrice.getDate(),
                        historicalPrice.getPrice()
                );
    }

    @Test
    void givenTheSameHistoricalPriceJPATwiceWhenFindHighestAndMostRecentThenReturnAnyHistoricalPriceJPA(){
        HistoricalPriceJPA historicalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 1, 1));

        HistoricalPriceJPA theSameHistoricalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 1, 1));

        HistoricalPriceJPA highestAndMostRecentHistoricalPrice =
                historicalPriceService.findHighestAndMostRecent(Arrays.asList(historicalPrice, theSameHistoricalPrice));

        Assertions.assertThat(highestAndMostRecentHistoricalPrice)
                .extracting("instrument", "date", "price")
                .contains(
                        historicalPrice.getInstrument(),
                        historicalPrice.getDate(),
                        historicalPrice.getPrice()
                );
    }

    @Test
    void givenHigherHistoricalPriceValueWhenFindHighestAndMostRecentThenReturnHigherHistoricalPriceValue(){
        HistoricalPriceJPA lowerHistoricalPriceValue =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 1, 1));

        HistoricalPriceJPA higherHistoricalPriceValue =
                createHistoricalPriceJPA(20.0, LocalDate.of(2020, 1, 1));

        HistoricalPriceJPA highestAndMostRecentHistoricalPrice =
                historicalPriceService.findHighestAndMostRecent(Arrays.asList(lowerHistoricalPriceValue, higherHistoricalPriceValue));

        Assertions.assertThat(highestAndMostRecentHistoricalPrice)
                .extracting("instrument", "date", "price")
                .contains(
                        higherHistoricalPriceValue.getInstrument(),
                        higherHistoricalPriceValue.getDate(),
                        higherHistoricalPriceValue.getPrice()
                );
    }

    @Test
    void givenRecentHistoricalPriceDateWhenFindHighestAndMostRecentThenReturnRecentHistoricalPriceDate(){
        HistoricalPriceJPA olderHistoricalPriceDate =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 1, 1));

        HistoricalPriceJPA recentHistoricalPriceDate =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 2, 10));

        HistoricalPriceJPA highestAndMostRecentHistoricalPrice  =
                historicalPriceService.findHighestAndMostRecent(Arrays.asList(olderHistoricalPriceDate, recentHistoricalPriceDate));

        Assertions.assertThat(highestAndMostRecentHistoricalPrice)
                .extracting("instrument", "date", "price")
                .contains(
                        recentHistoricalPriceDate.getInstrument(),
                        recentHistoricalPriceDate.getDate(),
                        recentHistoricalPriceDate.getPrice()
                );
    }

    @Test
    void givenHigherAndRecentHistoricalPriceWhenFindHighestAndMostRecentThenReturnHigherAndRecentHistoricalPrice(){
        HistoricalPriceJPA lowerAndOlderHistoricalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 1, 1));

        HistoricalPriceJPA higherAndRecentHistoricalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 2, 10));

        HistoricalPriceJPA highestAndMostRecentHistoricalPrice =
                historicalPriceService.findHighestAndMostRecent(Arrays.asList(lowerAndOlderHistoricalPrice, higherAndRecentHistoricalPrice));

        Assertions.assertThat(highestAndMostRecentHistoricalPrice)
                .extracting("instrument", "date", "price")
                .contains(
                        higherAndRecentHistoricalPrice.getInstrument(),
                        higherAndRecentHistoricalPrice.getDate(),
                        higherAndRecentHistoricalPrice.getPrice()
                );
    }

    @Test
    void givenHigherAndRecentHistoricalPriceFirstWhenFindHighestAndMostRecentThenReturnHigherAndRecentHistoricalPrice(){
        HistoricalPriceJPA lowerHistoricalPrice =
                createHistoricalPriceJPA(9.0, LocalDate.of(2020, 2, 10));

        HistoricalPriceJPA olderHistoricalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 1, 1));

        HistoricalPriceJPA higherAndRecentHistoricalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 2, 10));

        HistoricalPriceJPA highestAndMostRecentHistoricalPrice =
                historicalPriceService.findHighestAndMostRecent(
                        Arrays.asList(higherAndRecentHistoricalPrice, lowerHistoricalPrice, olderHistoricalPrice));

        Assertions.assertThat(highestAndMostRecentHistoricalPrice)
                .extracting("instrument", "date", "price")
                .contains(
                        higherAndRecentHistoricalPrice.getInstrument(),
                        higherAndRecentHistoricalPrice.getDate(),
                        higherAndRecentHistoricalPrice.getPrice()
                );
    }

    @Test
    void givenHigherAndRecentHistoricalPriceSecondWhenFindHighestAndMostRecentThenReturnHigherAndRecentHistoricalPrice(){
        HistoricalPriceJPA lowerHistoricalPrice =
                createHistoricalPriceJPA(9.0, LocalDate.of(2020, 2, 10));

        HistoricalPriceJPA olderHistoricalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 1, 1));

        HistoricalPriceJPA higherAndRecentHistoricalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 2, 10));

        HistoricalPriceJPA highestAndMostRecentHistoricalPrice =
                historicalPriceService.findHighestAndMostRecent(
                        Arrays.asList(lowerHistoricalPrice, higherAndRecentHistoricalPrice, olderHistoricalPrice));

        Assertions.assertThat(highestAndMostRecentHistoricalPrice)
                .extracting("instrument", "date", "price")
                .contains(
                        higherAndRecentHistoricalPrice.getInstrument(),
                        higherAndRecentHistoricalPrice.getDate(),
                        higherAndRecentHistoricalPrice.getPrice()
                );
    }

    @Test
    void givenHigherAndRecentHistoricalPriceThirdWhenFindHighestAndMostRecentThenReturnHigherAndRecentHistoricalPrice(){
        HistoricalPriceJPA lowerHistoricalPrice =
                createHistoricalPriceJPA(9.0, LocalDate.of(2020, 2, 10));

        HistoricalPriceJPA olderHistoricalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 1, 1));

        HistoricalPriceJPA higherAndRecentHistoricalPrice =
                createHistoricalPriceJPA(10.0, LocalDate.of(2020, 2, 10));

        HistoricalPriceJPA highestAndMostRecentHistoricalPrice =
                historicalPriceService.findHighestAndMostRecent(
                        Arrays.asList(lowerHistoricalPrice, olderHistoricalPrice, higherAndRecentHistoricalPrice));

        Assertions.assertThat(highestAndMostRecentHistoricalPrice)
                .extracting("instrument", "date", "price")
                .contains(
                        higherAndRecentHistoricalPrice.getInstrument(),
                        higherAndRecentHistoricalPrice.getDate(),
                        higherAndRecentHistoricalPrice.getPrice()
                );
    }

    private HistoricalPriceJPA createHistoricalPriceJPA(double price, LocalDate date) {
        InstrumentJPA instrumentJPA = new InstrumentJPA();
        instrumentJPA.setId(1L);
        instrumentJPA.setSymbol("PETR4");
        instrumentJPA.setMaturityDate(LocalDate.of(2020,12,31));

        HistoricalPriceJPA historicalPriceJPA = new HistoricalPriceJPA();
        historicalPriceJPA.setInstrument(instrumentJPA);
        historicalPriceJPA.setDate(date);
        historicalPriceJPA.setPrice(price);

        return historicalPriceJPA;
    }

}