package br.com.b3.instrument.backend.controller.converter;

import br.com.b3.instrument.backend.controller.model.HistoricalPriceResponse;
import br.com.b3.instrument.backend.data.jpa.model.HistoricalPriceJPA;
import br.com.b3.instrument.backend.data.json.model.HistoricalPriceJSON;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class HistoricalPriceConverter {

    static final String ERROR_MESSAGE_MANDATORY_PRICE = "Price(s) can not be null";

    public static HistoricalPriceResponse convert(HistoricalPriceJPA price){
        return convert(Collections.singletonList(price));
    }

    public static HistoricalPriceResponse convert(List<HistoricalPriceJPA> prices){
        Assert.notNull(prices, ERROR_MESSAGE_MANDATORY_PRICE);

        return prices.stream().reduce(new HistoricalPriceResponse(),

                (response, historicalPriceJPA) -> {
                    HistoricalPriceResponse.Serie serie = new HistoricalPriceResponse.Serie();
                    serie.setDate(historicalPriceJPA.getDate());
                    serie.setPrice(historicalPriceJPA.getPrice());

                    response.getSeries().add(serie);
                    response.setSymbol(historicalPriceJPA.getInstrument().getSymbol());

                    return response;

                }, (accumulator, historicalPriceResponse) -> {
                    HashSet<HistoricalPriceResponse.Serie> uniqueSeries = new HashSet<>(accumulator.getSeries());
                    uniqueSeries.addAll(historicalPriceResponse.getSeries());

                    accumulator.setSeries(new ArrayList<>(uniqueSeries));
                    accumulator.setSymbol(historicalPriceResponse.getSymbol());

                    return accumulator;
                });
    }

    public static HistoricalPriceResponse convert(HistoricalPriceJSON price){
        Assert.notNull(price, ERROR_MESSAGE_MANDATORY_PRICE);

        HistoricalPriceResponse historicalPriceResponse = new HistoricalPriceResponse();
        historicalPriceResponse.setSymbol(price.getSymbol());

        if(Objects.nonNull(price.getSeries())){
            List<HistoricalPriceResponse.Serie> series = Arrays.stream(price.getSeries())
                    .map(HistoricalPriceSeriesConverter::convert)
                    .collect(Collectors.toList());
            historicalPriceResponse.setSeries(series);
        }

        return historicalPriceResponse;
    }

    private static class HistoricalPriceSeriesConverter {

        static HistoricalPriceResponse.Serie convert(HistoricalPriceJSON.Serie jsonSerie){
            HistoricalPriceResponse.Serie responseSerie = new HistoricalPriceResponse.Serie();
            responseSerie.setDate(jsonSerie.getDate());
            responseSerie.setPrice(jsonSerie.getPrice());
            return responseSerie;
        }

    }

}
