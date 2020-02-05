package br.com.b3.instrument.backend.repository.json;

import br.com.b3.instrument.backend.domain.Instrument;
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
public class InstrumentJsonRepository {

    private static final Logger logger = LoggerFactory.getLogger(InstrumentJsonRepository.class);
    private static final String JSON_DATASOURCE_NAME = "instruments.json";

    public List<Instrument> findAll() {
        try {
            String json = ResourceUtil.getContentAsString(JSON_DATASOURCE_NAME);
            return JSONUtil.parseAsList(json, Instrument.class);
        } catch (IOException | URISyntaxException e) {
            logger.warn("Problema ao extrair instrumentos do datasource {}", JSON_DATASOURCE_NAME, e);
        }
        return Collections.emptyList();
    }

}
