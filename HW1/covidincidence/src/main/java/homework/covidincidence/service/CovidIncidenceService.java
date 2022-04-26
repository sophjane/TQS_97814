package homework.covidincidence.service;

import java.net.URI;
import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import homework.covidincidence.Cache;
import homework.covidincidence.CustomHttpClient;

import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class CovidIncidenceService {

    private static final Logger LOGGER = LogManager.getLogger(CovidIncidenceService.class);

    final String baseURL = "https://covid-193.p.rapidapi.com/";

    private CustomHttpClient client = new CustomHttpClient();

    Cache cache = new Cache(5, 6);

    public ResponseEntity<String>  getCountries() {
        try {
            if(cache.get("countries") != null) {
                LOGGER.debug("GET");
                LOGGER.info("Service: Get countries from cache");

                return new ResponseEntity<>((String)cache.get("countries"), HttpStatus.OK);
            }
            HttpResponse<String> response = client.getHttpResponse(URI.create(baseURL + "countries"));
            LOGGER.info("Service: Get countries from API and Put in cache");
            cache.put("countries", response.body());
            LOGGER.debug("PUT");

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Service: Get Countries");

            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String>  getStatistics(String country) {
        try {
            URIBuilder uriBuilder = new URIBuilder(baseURL + "statistics");

            if (country != null) {
                uriBuilder.addParameter("country", country);
            }
            URI uri = uriBuilder.build();
            HttpResponse<String> response = client.getHttpResponse(uri);
            LOGGER.info(country != null ? "Service: Get Statistics of " + country + "  from API" :  "Service: Get Statistics from API");

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Service: Get Statistics");
            
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String>  getCountryHistory(String country, String day) {
        try {
            URIBuilder uriBuilder = new URIBuilder(baseURL + "history")
                .addParameter("country", country);

            if (day != null) {
                uriBuilder.addParameter("day", day);
            }
            URI uri = uriBuilder.build();
            HttpResponse<String> response = client.getHttpResponse(uri);
            LOGGER.info(day != null ? "Service: Get History of " + country + " on the " + day + " from API" :  "Service: Get History of " + country + " from API");

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Service: Get History");

            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    
}
