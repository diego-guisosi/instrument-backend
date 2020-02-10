package br.com.b3.instrument.backend.service;

import br.com.b3.instrument.backend.data.json.repository.SessionDateJSONRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class SessionDateService {

    private final LocalDate currentSessionDate;

    @Autowired
    public SessionDateService(SessionDateJSONRepository sessionDateJSONRepository) {
        currentSessionDate = sessionDateJSONRepository.get()
                .map(sessionDateJSON ->
                        LocalDate.parse(
                                sessionDateJSON.getCurrentDate(),
                                DateTimeFormatter.ofPattern(sessionDateJSON.getDateFormat())))
                .orElseThrow(() -> new IllegalStateException("Impossible to determine current session date"));
    }

    public LocalDate getCurrentSessionDate() {
        return currentSessionDate;
    }

}
