package br.com.b3.instrument.backend.data.jpa.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tbl_hist_price")
@IdClass(HistoricalPriceIdJPA.class)
public class HistoricalPriceJPA {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instr_id")
    private InstrumentJPA instrument;

    @Id
    @Column(name = "hist_price_date")
    private LocalDate date;

    @Column(name = "hist_price_value")
    private Double price;

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
        HistoricalPriceJPA that = (HistoricalPriceJPA) o;
        return Objects.equals(instrument, that.instrument) &&
                Objects.equals(date, that.date) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instrument, date, price);
    }

    @Override
    public String toString() {
        return "HistoricalPriceJPA{" +
                "instrument=" + instrument +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
