package br.com.b3.instrument.backend.util;

import br.com.b3.instrument.backend.data.json.model.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

class JSONUtilTest {

    private static final String JSON_RESOURCE_NAME_LIST = "cars.json";
    private static final String JSON_RESOURCE_NAME_SINGLE_TYPE = "car.json";

    private static final int EXPECTED_CARS_QUANTITY = 2;

    private static final String FERRARI_NAME = "Ferrari";
    private static final String FERRARI_COLOR = "blue";

    private static final String PORSCHE_NAME = "Porsche";
    private static final String PORSCHE_COLOR = "yellow";

    @Test
    void givenBothNullWhenParseAsListThenReturnEmptyList() throws IOException {

        List<Object> objects = JSONUtil.parseAsList(null, null);

        Assertions.assertThat(objects).isEmpty();

    }

    @Test
    void givenNullJsonStringWhenParseAsListThenReturnEmptyList() throws IOException {

        List<Car> cars = JSONUtil.parseAsList(null, Car.class);

        Assertions.assertThat(cars).isEmpty();

    }

    @Test
    void givenNullClassWhenParseAsListThenReturnEmptyList() throws IOException, URISyntaxException {

        String jsonString = ResourceUtil.getContentAsString(JSON_RESOURCE_NAME_LIST);

        List<Car> cars = JSONUtil.parseAsList(jsonString, null);

        Assertions.assertThat(cars).isEmpty();

    }

    @Test
    void givenDataAndClassWhenParseAsListThenReturnNonEmptyList() throws IOException, URISyntaxException {

        String jsonString = ResourceUtil.getContentAsString(JSON_RESOURCE_NAME_LIST);

        List<Car> cars = JSONUtil.parseAsList(jsonString, Car.class);

        Assertions.assertThat(cars)
                .hasSize(EXPECTED_CARS_QUANTITY)
                .extracting("name", "color")
                .contains(
                        Assertions.tuple(FERRARI_NAME, FERRARI_COLOR),
                        Assertions.tuple(PORSCHE_NAME, PORSCHE_COLOR)
                );

    }

    @Test
    void givenBothNullWhenParseAsSingleTypeThenReturnOptionalEmpty() throws IOException {

        Optional<Object> optional = JSONUtil.parseAsSingleType(null, null);

        Assertions.assertThat(optional).isNotPresent();

    }

    @Test
    void givenNullJsonStringWhenParseAsSingleTypeThenReturnOptionalEmpty() throws IOException {

        Optional<Car> optionalCar = JSONUtil.parseAsSingleType(null, Car.class);

        Assertions.assertThat(optionalCar).isNotPresent();

    }

    @Test
    void givenNullClassWhenParseAsSingleTypeThenReturnOptionalEmpty() throws IOException, URISyntaxException {

        String jsonString = ResourceUtil.getContentAsString(JSON_RESOURCE_NAME_SINGLE_TYPE);

        Optional<Object> optionalCar = JSONUtil.parseAsSingleType(jsonString, null);

        Assertions.assertThat(optionalCar).isNotPresent();

    }

    @Test
    void givenDataAndClassWhenParseAsSingleTypeThenReturnParsedType() throws IOException, URISyntaxException {

        String jsonString = ResourceUtil.getContentAsString(JSON_RESOURCE_NAME_SINGLE_TYPE);

        Optional<Car> optionalCar = JSONUtil.parseAsSingleType(jsonString, Car.class);

        Assertions.assertThat(optionalCar)
                .isPresent()
                .get()
                .extracting("name", "color")
                .contains(FERRARI_NAME, FERRARI_COLOR);

    }

}