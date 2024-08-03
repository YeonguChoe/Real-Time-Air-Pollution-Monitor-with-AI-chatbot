package ca.terrahacks.backend.pollutant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pollutant")
public class PollutantController {

    private final PollutantService service;

//    @Autowired
    public PollutantController(PollutantService pollutantService) {
        this.service = pollutantService;
    }

    @GetMapping
    public List<Pollutant> getPollutants() {
        return this.service.getPollutant();
    }

}
