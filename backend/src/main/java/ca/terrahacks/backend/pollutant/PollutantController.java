package ca.terrahacks.backend.pollutant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/pollutant")
public class PollutantController {

    private final PollutantService service;

    @Autowired
    public PollutantController(PollutantService pollutantService) {
        this.service = pollutantService;
    }

    @CrossOrigin(origins = {"http://localhost:3000"})
    @GetMapping
    public Pollutant getPollutants(@RequestParam double latitude, @RequestParam double longitude) {
        return this.service.getPollutant(latitude, longitude);
    }
}