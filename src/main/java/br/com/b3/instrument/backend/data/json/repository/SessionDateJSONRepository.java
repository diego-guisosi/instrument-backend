package br.com.b3.instrument.backend.data.json.repository;

import br.com.b3.instrument.backend.data.json.model.SessionDateJSON;
import br.com.b3.instrument.backend.util.JSONUtil;
import br.com.b3.instrument.backend.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

@Repository
public class SessionDateJSONRepository {

    private static final Logger logger = LoggerFactory.getLogger(SessionDateJSONRepository.class);
    private static final String JSON_DATASOURCE_NAME = "date.json";

    public Optional<SessionDateJSON> get() {
        try {
            String json = ResourceUtil.getContentAsString(JSON_DATASOURCE_NAME);
            return JSONUtil.parseAsSingleType(json, SessionDateJSON.class);
        } catch (IOException | URISyntaxException e) {
            logger.warn("It was not possible to extract session date from datasource {}", JSON_DATASOURCE_NAME, e);
        }
        return Optional.empty();
    }

}
