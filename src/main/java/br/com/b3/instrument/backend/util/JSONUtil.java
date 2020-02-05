package br.com.b3.instrument.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class JSONUtil {

    public static <T> List<T> parseAsList(String jsonString, Class<T> classOfJsonType) throws IOException {

        if(Objects.isNull(jsonString) || Objects.isNull(classOfJsonType)){
            return Collections.emptyList();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        CollectionType collectionType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, classOfJsonType);

        return objectMapper.readValue(jsonString, collectionType);

    }



}
