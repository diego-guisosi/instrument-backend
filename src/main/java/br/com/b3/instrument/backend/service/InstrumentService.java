package br.com.b3.instrument.backend.service;

import br.com.b3.instrument.backend.data.jpa.model.InstrumentJPA;
import br.com.b3.instrument.backend.data.jpa.repository.InstrumentJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstrumentService {

    @Autowired
    private InstrumentJPARepository instrumentRepository;

    public List<InstrumentJPA> findAllSortedByMaturityDate(){
        return instrumentRepository.findAllOrderByMaturityDateAsc();
    }

}
