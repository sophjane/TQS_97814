package homework.covidincidence.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import homework.covidincidence.service.CovidIncidenceService;

@RestController
@RequestMapping("/api")
public class CovidIncidenceController {

    private static final Logger LOGGER = LogManager.getLogger(CovidIncidenceController.class);

    @Autowired
    public CovidIncidenceService covidIncidenceService;

    public CovidIncidenceController(CovidIncidenceService covidIncidenceService) {
        this.covidIncidenceService = covidIncidenceService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/countries")
    public ResponseEntity<String> getCountries() throws IOException {
        LOGGER.info("Controller: Get Countries");
        return covidIncidenceService.getCountries();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/statistics")
    public ResponseEntity<String> getStatistics(@RequestParam(required = false) String country) throws URISyntaxException, IOException {
        
        if(country != null) {
            LOGGER.info(String.format("Controller: Get Statistics of %s", country));
        }
        LOGGER.info("Controller: Get Statistics");
        return covidIncidenceService.getStatistics(country);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/history")
    public ResponseEntity<String> getHistory(@RequestParam String country, @RequestParam(required = false) String day) throws URISyntaxException, IOException {
        if(day != null) {
            LOGGER.info(String.format("Controller: Get Statistics of %s, day: %s", country, day));
        } else {
            LOGGER.info(String.format("Controller: Get Statistics of %s", country));
        }
        return covidIncidenceService.getCountryHistory(country, day);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/cache")
    public ResponseEntity<String> getCacheUsageStats() {
        return covidIncidenceService.getCacheUsageStats();
    }
}
