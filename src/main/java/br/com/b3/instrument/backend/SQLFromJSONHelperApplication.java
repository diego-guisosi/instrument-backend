package br.com.b3.instrument.backend;

import br.com.b3.instrument.backend.data.json.model.HistoricalPriceJSON;
import br.com.b3.instrument.backend.data.json.model.InstrumentJSON;
import br.com.b3.instrument.backend.util.JSONUtil;
import br.com.b3.instrument.backend.util.ResourceUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SQLFromJSONHelperApplication {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<InstrumentJSON> instruments = extractEntities("instruments.json", InstrumentJSON.class);
        print(instruments,
                instrument -> String.format("INSERT INTO tbl_instr (instr_id, instr_symbol, instr_mat_date) " +
                                "VALUES (%d, '%s', parsedatetime('%s', 'yyyy-MM-dd'));",
                        instrument.getId(), instrument.getSymbol(), instrument.getMaturityDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));

        System.out.println();
        System.out.println();

        Map<String, Long> instrumentIdBySymbol = instruments.stream().collect(Collectors.toMap(InstrumentJSON::getSymbol, InstrumentJSON::getId));
        List<HistoricalPriceJSON> historicalPrices = extractEntities("prices.json", HistoricalPriceJSON.class);
        print(historicalPrices, price -> {
            Long instrumentId = instrumentIdBySymbol.get(price.getSymbol());
            return Arrays.stream(price.getSeries())
                    .map(serie -> String.format("INSERT INTO tbl_hist_price (instr_id, hist_price_date, hist_price_value) " +
                                    "VALUES (%d, parsedatetime('%s', 'yyyy-MM-dd'), %.0f);",
                            instrumentId, serie.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE), serie.getPrice()))
                    .collect(Collectors.joining("\n"));
        });
    }

    private static <T> List<T> extractEntities(String resourceName, Class<T> resourceType) throws IOException, URISyntaxException {
        String jsonString = ResourceUtil.getContentAsString(resourceName);
        return JSONUtil.parseAsList(jsonString, resourceType);
    }

    private static <T> void print(List<T> entities, Function<T, String> converterFunction) {
        entities.stream()
                .map(converterFunction)
                .forEach(System.out::println);
    }

}
