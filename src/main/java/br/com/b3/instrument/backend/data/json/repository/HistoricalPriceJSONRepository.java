package br.com.b3.instrument.backend.data.json.repository;

import br.com.b3.instrument.backend.data.json.model.HistoricalPriceJSON;
import br.com.b3.instrument.backend.util.JSONUtil;
import br.com.b3.instrument.backend.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@Repository
public class HistoricalPriceJSONRepository {

    private static final Logger logger = LoggerFactory.getLogger(HistoricalPriceJSONRepository.class);
    private static final String JSON_DATASOURCE_NAME = "prices.json";

    public List<HistoricalPriceJSON> findAll() {
        try {
            String json = ResourceUtil.getContentAsString(JSON_DATASOURCE_NAME);
            return JSONUtil.parseAsList(json, HistoricalPriceJSON.class);
        } catch (IOException | URISyntaxException e) {
            logger.warn("It was not possible to extract prices from datasource {}", JSON_DATASOURCE_NAME, e);
        }
        return Collections.emptyList();
    }

}
