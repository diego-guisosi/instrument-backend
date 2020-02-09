package br.com.b3.instrument.backend.controller.converter;

import br.com.b3.instrument.backend.controller.model.HistoricalPriceResponse;
import br.com.b3.instrument.backend.data.json.model.HistoricalPriceJSON;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HistoricalPriceConverter {

    static final String ERROR_MESSAGE_MANDATORY_PRICE = "Price can not be null";

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
