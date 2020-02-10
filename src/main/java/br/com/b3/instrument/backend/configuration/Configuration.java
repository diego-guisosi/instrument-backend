package br.com.b3.instrument.backend.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Configuration {

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    public static final String HISTORICAL_MAXIMUM_DAYS = "instrumentprice.historical.maximumDays";

    @Autowired
    private Environment environment;

    public Optional<Long> getValueAsLong(String configurationName) {
        Optional<String> propertyValue = getValueAsString(configurationName);
        try {
            return propertyValue.map(Long::parseLong);
        } catch (Exception e){
            logger.warn("Impossible to parse configuration '{}={}' as Long", configurationName, propertyValue);
            return Optional.empty();
        }
    }

    private Optional<String> getValueAsString(String configurationName) {
        try {
            return Optional.ofNullable(environment.getProperty(configurationName));
        } catch (Exception e){
            logger.warn("Value of configuration '{}' not found", configurationName);
            return Optional.empty();
        }
    }


}
