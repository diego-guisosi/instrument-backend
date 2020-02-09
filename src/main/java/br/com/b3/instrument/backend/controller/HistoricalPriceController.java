package br.com.b3.instrument.backend.controller;

import br.com.b3.instrument.backend.controller.converter.HistoricalPriceConverter;
import br.com.b3.instrument.backend.controller.model.HistoricalPriceResponse;
import br.com.b3.instrument.backend.service.HistoricalPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prices")
public class HistoricalPriceController {

    @Autowired
    private HistoricalPriceService historicalPriceService;

    @GetMapping
    public List<HistoricalPriceResponse> getAll() {
        return historicalPriceService.findAll()
                .stream()
                .map(HistoricalPriceConverter::convert)
                .collect(Collectors.toList());
    }

}
