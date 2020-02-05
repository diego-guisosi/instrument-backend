package br.com.b3.instrument.backend.service;

import br.com.b3.instrument.backend.domain.Instrument;
import br.com.b3.instrument.backend.repository.json.InstrumentJsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstrumentService {

    @Autowired
    private InstrumentJsonRepository instrumentRepository;

    public List<Instrument> findAll(){
        return instrumentRepository.findAll();
    }

}
