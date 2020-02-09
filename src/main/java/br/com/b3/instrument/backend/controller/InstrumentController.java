package br.com.b3.instrument.backend.controller;

import br.com.b3.instrument.backend.controller.converter.InstrumentConverter;
import br.com.b3.instrument.backend.controller.model.InstrumentResponse;
import br.com.b3.instrument.backend.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;

    @GetMapping("/instruments")
    public List<InstrumentResponse> getAll() {
        return instrumentService.findAllSortedByMaturityDate()
                .stream()
                .map(InstrumentConverter::convert)
                .collect(Collectors.toList());
    }

}
