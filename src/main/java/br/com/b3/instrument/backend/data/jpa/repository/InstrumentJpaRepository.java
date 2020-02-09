package br.com.b3.instrument.backend.data.jpa.repository;

import br.com.b3.instrument.backend.data.jpa.model.InstrumentJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentJpaRepository extends CrudRepository<InstrumentJPA, Long> {

    @Query("select i from InstrumentJPA i order by i.maturityDate asc")
    List<InstrumentJPA> findAllOrderByMaturityDateAsc();

}
