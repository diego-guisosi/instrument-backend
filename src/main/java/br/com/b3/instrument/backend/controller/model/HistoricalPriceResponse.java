package br.com.b3.instrument.backend.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class HistoricalPriceResponse {

    private String symbol;

    private List<Serie> series;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricalPriceResponse that = (HistoricalPriceResponse) o;
        return Objects.equals(symbol, that.symbol) &&
                Objects.equals(series, that.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, series);
    }

    @Override
    public String toString() {
        return "HistoricalPriceResponse{" +
                "symbol='" + symbol + '\'' +
                ", series=" + series +
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
            HistoricalPriceResponse.Serie serie = (HistoricalPriceResponse.Serie) o;
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
