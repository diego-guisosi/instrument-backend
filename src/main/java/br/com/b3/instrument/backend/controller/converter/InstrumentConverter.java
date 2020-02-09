package br.com.b3.instrument.backend.controller.converter;

import br.com.b3.instrument.backend.controller.model.InstrumentResponse;
import br.com.b3.instrument.backend.data.jpa.model.InstrumentJPA;
import br.com.b3.instrument.backend.data.json.model.InstrumentJSON;
import org.springframework.util.Assert;

public class InstrumentConverter {

    static final String ERROR_MESSAGE_MANDATORY_INSTRUMENT = "Instrument can not be null";

    public static InstrumentResponse convert(InstrumentJPA instrument){
        Assert.notNull(instrument, ERROR_MESSAGE_MANDATORY_INSTRUMENT);

        InstrumentResponse instrumentResponse = new InstrumentResponse();
        instrumentResponse.setSymbol(instrument.getSymbol());
        instrumentResponse.setMaturityDate(instrument.getMaturityDate());
        return instrumentResponse;
    }

    public static InstrumentResponse convert(InstrumentJSON instrument){
        Assert.notNull(instrument, ERROR_MESSAGE_MANDATORY_INSTRUMENT);

        InstrumentResponse instrumentResponse = new InstrumentResponse();
        instrumentResponse.setSymbol(instrument.getSymbol());
        instrumentResponse.setMaturityDate(instrument.getMaturityDate());
        return instrumentResponse;
    }

}
