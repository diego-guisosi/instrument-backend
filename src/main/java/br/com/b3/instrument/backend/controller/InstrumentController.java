package br.com.b3.instrument.backend.controller;

import br.com.b3.instrument.backend.domain.Instrument;
import br.com.b3.instrument.backend.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;

    @GetMapping("/instruments")
    public List<Instrument> getAll() {
        return instrumentService.findAll();
    }

}
