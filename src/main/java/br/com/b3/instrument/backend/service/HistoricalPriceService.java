package br.com.b3.instrument.backend.service;

import br.com.b3.instrument.backend.configuration.Configuration;
import br.com.b3.instrument.backend.data.jpa.model.HistoricalPriceJPA;
import br.com.b3.instrument.backend.data.jpa.repository.HistoricalPriceJPARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistoricalPriceService {

    private static final Logger logger = LoggerFactory.getLogger(HistoricalPriceService.class);

    private static final Long DEFAULT_MAXIMUM_DAYS = 10L;

    @Autowired
    private HistoricalPriceJPARepository historicalPriceRepository;

    @Autowired
    private SessionDateService sessionDateService;

    @Autowired
    private Configuration configuration;

    public List<HistoricalPriceJPA> findAll(){
        return (List<HistoricalPriceJPA>) historicalPriceRepository.findAll();
    }

    public List<HistoricalPriceJPA> findBySymbolLimitedByMaximumDateRange(String symbol){

        Long maximumDays = configuration.getValueAsLong(Configuration.HISTORICAL_MAXIMUM_DAYS).orElseGet(() -> {
            logger.warn("Default maximum days value '{}' is being used instead of the configured value", DEFAULT_MAXIMUM_DAYS);
            return DEFAULT_MAXIMUM_DAYS;
        });

        LocalDate currentSessionDate = sessionDateService.getCurrentSessionDate();
        LocalDate oldestValidDate = currentSessionDate.minusDays(maximumDays);

        return historicalPriceRepository.findBySymbolAndDateRange(symbol, oldestValidDate, currentSessionDate);
    }

    public HistoricalPriceJPA findHighestAndMostRecentBySymbolLimitedByMaximumDateRange(String symbol){
        List<HistoricalPriceJPA> historicalPrices = findBySymbolLimitedByMaximumDateRange(symbol);
        return findHighestAndMostRecent(historicalPrices);
    }

    HistoricalPriceJPA findHighestAndMostRecent(List<HistoricalPriceJPA> historicalPrices) {
        return historicalPrices.stream()
                .collect(Collectors.groupingBy(HistoricalPriceJPA::getPrice))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .map(maximumHistoricalPrices -> maximumHistoricalPrices.stream()
                        .max(Comparator.comparing(HistoricalPriceJPA::getDate))
                        .orElse(new HistoricalPriceJPA()))
                .orElse(new HistoricalPriceJPA());
    }

}
