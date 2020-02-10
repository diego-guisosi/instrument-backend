package br.com.b3.instrument.backend.data.json.repository;

import br.com.b3.instrument.backend.data.json.model.SessionDateJSON;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SessionDateJSONRepositoryIT {

    @Autowired
    private SessionDateJSONRepository sessionDateJSONRepository;

    @Test
    void givenDateJsonFileWhenGetThenReturnSessionDateJSON(){
        Optional<SessionDateJSON> optionalSessionDateJSON = sessionDateJSONRepository.get();

        Assertions.assertThat(optionalSessionDateJSON)
                .isPresent()
                .get()
                .extracting("currentDate", "dateFormat")
                .contains("2020-02-10", "yyyy-MM-dd");
    }

}