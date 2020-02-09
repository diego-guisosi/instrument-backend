package br.com.b3.instrument.backend.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;
import java.util.Objects;

public class InstrumentResponse {

    private String symbol;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate maturityDate;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstrumentResponse that = (InstrumentResponse) o;
        return Objects.equals(symbol, that.symbol) &&
                Objects.equals(maturityDate, that.maturityDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, maturityDate);
    }

    @Override
    public String toString() {
        return "InstrumentResponse{" +
                "symbol='" + symbol + '\'' +
                ", maturityDate=" + maturityDate +
                '}';
    }
}
