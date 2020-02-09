package br.com.b3.instrument.backend.service;

import br.com.b3.instrument.backend.data.json.model.HistoricalPriceJSON;
import br.com.b3.instrument.backend.data.json.repository.HistoricalPriceJSONRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricalPriceService {

    @Autowired
    private HistoricalPriceJSONRepository historicalPriceRepository;

    public List<HistoricalPriceJSON> findAll(){
        return historicalPriceRepository.findAll();
    }

}
