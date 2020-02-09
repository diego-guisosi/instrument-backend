package br.com.b3.instrument.backend.data.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class HistoricalPriceJSON {

    private String symbol;

    private Serie[] series;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Serie[] getSeries() {
        return series;
    }

    public void setSeries(Serie[] series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricalPriceJSON that = (HistoricalPriceJSON) o;
        return Objects.equals(symbol, that.symbol) &&
                Arrays.equals(series, that.series);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(symbol);
        result = 31 * result + Arrays.hashCode(series);
        return result;
    }

    @Override
    public String toString() {
        return "HistoricalPriceJSON{" +
                "symbol='" + symbol + '\'' +
                ", series=" + Arrays.toString(series) +
                '}';
    }

    public static class Serie {

        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
        private LocalDate date;

        private Double price;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Serie serie = (Serie) o;
            return Objects.equals(date, serie.date) &&
                    Objects.equals(price, serie.price);
        }

        @Override
        public int hashCode() {
            return Objects.hash(date, price);
        }

        @Override
        public String toString() {
            return "Serie{" +
                    "date=" + date +
                    ", price=" + price +
                    '}';
        }
    }
}
