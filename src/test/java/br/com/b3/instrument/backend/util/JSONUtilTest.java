package br.com.b3.instrument.backend.util;

import br.com.b3.instrument.backend.domain.Car;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class JSONUtilTest {

    private static final String JSON_RESOURCE_NAME = "cars.json";

    private static final int EXPECTED_CARS_QUANTITY = 2;

    private static final String FERRARI_NAME = "Ferrari";
    private static final String FERRARI_COLOR = "blue";

    private static final String PORSCHE_NAME = "Porsche";
    private static final String PORSCHE_COLOR = "yellow";

    @Test
    void givenBothNullWhenParseAsListThenReturnEmptyList() throws IOException {

        List<Object> cars = JSONUtil.parseAsList(null, null);

        assertTrue(cars.isEmpty());

    }

    @Test
    void givenNullJsonStringWhenParseAsListThenReturnEmptyList() throws IOException {

        List<Car> cars = JSONUtil.parseAsList(null, Car.class);

        assertTrue(cars.isEmpty());

    }

    @Test
    void givenNullClassWhenParseAsListThenReturnEmptyList() throws IOException, URISyntaxException {

        String jsonString = ResourceUtil.getContentAsString(JSON_RESOURCE_NAME);

        List<Car> cars = JSONUtil.parseAsList(jsonString, null);

        assertTrue(cars.isEmpty());

    }

    @Test
    void givenDataAndClassWhenParseFromBytesThenReturnOptionalEmpty() throws IOException, URISyntaxException {

        String jsonString = ResourceUtil.getContentAsString("cars.json");

        List<Car> cars = JSONUtil.parseAsList(jsonString, Car.class);

        assertEquals(EXPECTED_CARS_QUANTITY, cars.size());

        Set<String> carNames = cars.stream().map(Car::getName).collect(Collectors.toSet());
        assertEquals(EXPECTED_CARS_QUANTITY, carNames.size());
        assertTrue(carNames.contains(FERRARI_NAME));
        assertTrue(carNames.contains(PORSCHE_NAME));

        Set<String> carColors = cars.stream().map(Car::getColor).collect(Collectors.toSet());
        assertEquals(EXPECTED_CARS_QUANTITY, carColors.size());
        assertTrue(carColors.contains(FERRARI_COLOR));
        assertTrue(carColors.contains(PORSCHE_COLOR));

    }

}