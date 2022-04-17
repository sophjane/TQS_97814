package homework.covidincidence.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/countries")
    public ResponseEntity<String> getCountries() {
        LOGGER.info("Controller: Get Countries");
        return covidIncidenceService.getCountries();
    }

    @GetMapping("/statistics")
    public ResponseEntity<String> getStatistics(@RequestParam(required = false) String country) {
        LOGGER.info(country != null ? "Controller: Get Statistics of " + country : "Controller: Get Statistics");
        return covidIncidenceService.getStatistics(country);
    }

    @GetMapping("/history")
    public ResponseEntity<String> getHistory(@RequestParam String country, @RequestParam(required = false) String day) {
        LOGGER.info(day != null ? "Controller: Get Statistics of " + country + ", day: " + day : "Controller: Get Statistics of " + country);
        return covidIncidenceService.getCountryHistory(country, day);
    }
    
}
