package br.com.b3.instrument.backend.data.jpa.repository;

import br.com.b3.instrument.backend.data.jpa.model.HistoricalPriceIdJPA;
import br.com.b3.instrument.backend.data.jpa.model.HistoricalPriceJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoricalPriceJPARepository extends CrudRepository<HistoricalPriceJPA, HistoricalPriceIdJPA> {

    @Query("SELECT p from HistoricalPriceJPA p JOIN FETCH p.instrument" +
            "   WHERE p.instrument.symbol = :symbol" +
            "       AND p.date >= :minimumDate" +
            "       AND p.date <= :maximumDate")
    List<HistoricalPriceJPA> findBySymbolAndDateRange(@Param("symbol") String symbol,
                                                      @Param("minimumDate") LocalDate minimumDate,
                                                      @Param("maximumDate") LocalDate maximumDate);

}
