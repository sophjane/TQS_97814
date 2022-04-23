package homework.covidincidence;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import homework.covidincidence.service.CovidIncidenceService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CovidIncidenceServiceTest {


    // @Mock
    // private HttpClient client;

    // @Mock
    // private HttpResponse response;

    // @InjectMocks
    // private CovidIncidenceService service;

    // final String baseURL = "https://covid-193.p.rapidapi.com/";


    // final String host = "covid-193.p.rapidapi.com";

    // final String key = "e43a5d1d6fmsh454b6ef0f1e4428p104c4fjsnd533eb647f22";

    // @Test
    // void whenGetCountries_returnCountries() throws IOException, URISyntaxException, InterruptedException {

    //     String res = "{\"get\":\"countries\",\"parameters\":[],\"errors\":[],\"results\":233,\"response\":[\"Afghanistan\",\"Albania\"]}";

    //     // ResponseEntity<String> res = new ResponseEntity<>(response, HttpStatus.OK);

    //     when(client.send(request,  HttpResponse.BodyHandlers.ofString())).thenReturn(response);

    //     when(response.body()).thenReturn(res);

    //    assertEquals(response, service.getCountries());
    // }






}
