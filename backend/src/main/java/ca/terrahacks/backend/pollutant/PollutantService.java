package ca.terrahacks.backend.pollutant;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PollutantService {

    public List<Pollutant> getPollutant() {
        return List.of(
                new Pollutant("CO", 156.48, LocalDateTime.now())
        );
    }

}
