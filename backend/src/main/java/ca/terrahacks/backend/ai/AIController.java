package ca.terrahacks.backend.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ai")
public class AIController {
    private final AIService service;

    @Autowired
    public AIController(AIService service) {
        this.service = service;
    }

    @CrossOrigin(origins = {"http://localhost:3000"})
    @GetMapping
    public AI getAI(@RequestParam String city, @RequestParam String question, @RequestParam double CO, @RequestParam double NO2, @RequestParam double O3, @RequestParam double PM10, @RequestParam double PM25, @RequestParam double SO2) {
        return this.service.getAnswer(city, question, CO, NO2, O3, PM10, PM25, SO2);
    }
}
