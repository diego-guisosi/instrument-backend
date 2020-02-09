package br.com.b3.instrument.backend.data.jpa.repository;

import br.com.b3.instrument.backend.data.jpa.model.InstrumentJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstrumentJpaRepository extends CrudRepository<InstrumentJPA, Long> {

    @Query("select i from InstrumentJPA i order by i.maturityDate asc")
    List<InstrumentJPA> findAllOrderByMaturityDateAsc();

}
