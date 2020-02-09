package br.com.b3.instrument.backend.data.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tbl_instr")
public class InstrumentJPA {

    @Id
    @Column(name = "instr_id")
    private Long id;

    @Column(name = "instr_symbol")
    private String symbol;

    @Column(name = "instr_mat_date")
    private LocalDate maturityDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        InstrumentJPA that = (InstrumentJPA) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(maturityDate, that.maturityDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, maturityDate);
    }

    @Override
    public String toString() {
        return "InstrumentJPA{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", maturityDate=" + maturityDate +
                '}';
    }
}
