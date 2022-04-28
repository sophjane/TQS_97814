package homework.covidincidence;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CovidIncidenceAPIIT {

    @Autowired
    private TestRestTemplate restTemplate;
    ObjectMapper mapper = new ObjectMapper();
    
    @Test
    @Order(1)
    void whenGetCountries_thenStatus200() throws JsonMappingException, JsonProcessingException {
        
        
        ResponseEntity<String> entity = restTemplate.exchange("/api/countries", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});
        
        assertThat(entity.getStatusCodeValue()).isEqualTo(HttpStatus.SC_OK);
        
        Map<String,Object> map = mapper.readValue(entity.getBody(), Map.class);

        
        assertThat(map.get("response")).asList().hasSize(233).contains("Egypt");
    }

    @Test
    @Order(2)
    void whenGetStatistics_thenStatus200() throws JsonMappingException, JsonProcessingException{

        ResponseEntity<String> entity = restTemplate.exchange("/api/statistics", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});

        assertThat(entity.getStatusCodeValue()).isEqualTo(HttpStatus.SC_OK);

        Map<String,Object> map = mapper.readValue(entity.getBody(), Map.class);

        assertThat((map.get("response"))).asList().hasSize(240);

        List<Object> list = (List<Object>) map.get("response");
        Map stats = (Map) list.get(0);
        String continent = String.valueOf(stats.get("continent"));
       
        assertThat(continent).isEqualTo("Oceania");
    }

    @Test
    @Order(3)
    void whenGetCountryStatistics_thenStatus200() throws JsonMappingException, JsonProcessingException {

        ResponseEntity<String> entity = restTemplate.exchange("/api/statistics?country=portugal", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});

        assertThat(entity.getStatusCodeValue()).isEqualTo(HttpStatus.SC_OK);

        Map<String,Object> map = mapper.readValue(entity.getBody(), Map.class);

        assertThat((map.get("response"))).asList().hasSize(1);

        List<Object> list = (List<Object>) map.get("response");
        Map stats = (Map) list.get(0);
        String country = String.valueOf(stats.get("country"));
       
        assertThat(country).isEqualTo("Portugal");
    }

    @Test
    @Order(4)
    void whenGetCountryHistory_thenStatusCode200() throws JsonMappingException, JsonProcessingException {

        ResponseEntity<String> entity = restTemplate.exchange("/api/history?country=portugal", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});

        assertThat(entity.getStatusCode().value()).isEqualTo(HttpStatus.SC_OK);
        Map<String,Object> map = mapper.readValue(entity.getBody(), Map.class);

        assertThat((map.get("response"))).asList().hasSize(1585);

        List<Object> list = (List<Object>) map.get("response");
        Map stats = (Map) list.get(0);
        String country = String.valueOf(stats.get("country"));
       
        assertThat(country).isEqualTo("Portugal");
    }

    @Test
    @Order(5)
    void whenGetCountryDayHistory_thenStatusCode200() throws JsonMappingException, JsonProcessingException{

        ResponseEntity<String> entity = restTemplate.exchange("/api/history?country=portugal&day=2020-06-02", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});

        assertThat(entity.getStatusCode().value()).isEqualTo(HttpStatus.SC_OK);
        Map<String,Object> map = mapper.readValue(entity.getBody(), Map.class);

        assertThat((map.get("response"))).asList().hasSize(2);

        List<Object> list = (List<Object>) map.get("response");
        Map statsPosition0 = (Map) list.get(0);
        Map statsPosition1 = (Map) list.get(1);

        Map<String, String> params =  (Map<String, String>) map.get("parameters");;

        String countryPosition0 = String.valueOf(statsPosition0.get("country"));
        String countryPosition1 = String.valueOf(statsPosition1.get("country"));
       
        assertThat(countryPosition0).isEqualTo("Portugal");
        assertThat(countryPosition1).isEqualTo("Portugal");
        assertThat(params.get("country")).isEqualTo("portugal");
        assertThat(params.get("day")).isEqualTo("2020-06-02");
    }

}
