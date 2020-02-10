package br.com.b3.instrument.backend.controller;

import br.com.b3.instrument.backend.controller.converter.HistoricalPriceConverter;
import br.com.b3.instrument.backend.controller.model.HistoricalPriceResponse;
import br.com.b3.instrument.backend.data.jpa.model.HistoricalPriceJPA;
import br.com.b3.instrument.backend.service.HistoricalPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prices")
public class HistoricalPriceController {

    @Autowired
    private HistoricalPriceService historicalPriceService;

    @GetMapping
    public List<HistoricalPriceResponse> getAll() {
        return historicalPriceService.findAll()
                .stream()
                .collect(Collectors.groupingBy(HistoricalPriceJPA::getInstrument))
                .values()
                .stream()
                .map(HistoricalPriceConverter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("{symbol}")
    public HistoricalPriceResponse getBySymbol(@PathVariable("symbol") String symbol) {
        List<HistoricalPriceJPA> prices = historicalPriceService.findBySymbolLimitedByMaximumDateRange(symbol);
        return HistoricalPriceConverter.convert(prices);
    }

    @GetMapping("{symbol}/max")
    public Double getMaxBySymbol(@PathVariable("symbol") String symbol) {
        HistoricalPriceJPA price = historicalPriceService.findHighestAndMostRecentBySymbolLimitedByMaximumDateRange(symbol);
        HistoricalPriceResponse historicalPriceResponse = HistoricalPriceConverter.convert(price);
        return historicalPriceResponse.getSeries()
                .stream()
                .findAny()
                .map(HistoricalPriceResponse.Serie::getPrice)
                .orElse(null);
    }

}
