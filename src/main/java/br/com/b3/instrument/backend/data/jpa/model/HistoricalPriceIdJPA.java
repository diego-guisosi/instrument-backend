package br.com.b3.instrument.backend.data.jpa.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class HistoricalPriceIdJPA implements Serializable {

    private InstrumentJPA instrument;
    private LocalDate date;

    public HistoricalPriceIdJPA() {

    }

    public HistoricalPriceIdJPA(InstrumentJPA instrument, LocalDate date) {
        this.instrument = instrument;
        this.date = date;
    }

    public InstrumentJPA getInstrument() {
        return instrument;
    }

    public void setInstrument(InstrumentJPA instrument) {
        this.instrument = instrument;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricalPriceIdJPA that = (HistoricalPriceIdJPA) o;
        return Objects.equals(instrument, that.instrument) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instrument, date);
    }

    @Override
    public String toString() {
        return "HistoricalPriceIdJPA{" +
                "instrument=" + instrument +
                ", date=" + date +
                '}';
    }
}
