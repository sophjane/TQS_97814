package homework.covidincidence;

import java.net.http.HttpClient.Version;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.hc.core5.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import javax.net.ssl.SSLSession;

import java.util.Optional;

import homework.covidincidence.service.CovidIncidenceService;

@ExtendWith(MockitoExtension.class)
public class CovidIncidenceServiceTest {

    @Mock
    private CustomHttpClient client;

    @InjectMocks
    private CovidIncidenceService service;

    final String baseURL = "https://covid-193.p.rapidapi.com/";

    @Test
    void whenGetCountries_returnCountries() throws IOException, URISyntaxException, InterruptedException {

        String countries = "{\"get\":\"countries\",\"parameters\":[],\"errors\":[],\"results\":2,\"response\":[\"Afghanistan\",\"Albania\"]}";

        HttpResponse<String> response = createHttpResponse(countries);

        when(client.getHttpResponse(URI.create(baseURL+"countries"))).thenReturn(response);
        
        ResponseEntity<String> serviceResponseEntity = service.getCountries();

        assertEquals(response.body(), serviceResponseEntity.getBody());
        assertEquals(response.statusCode(), serviceResponseEntity.getStatusCode().value());

        verify(client, times(1)).getHttpResponse(URI.create(baseURL+"countries"));

    }


    @Test
    void whenGetStatistics_thenReturnStatistics() throws IOException, InterruptedException, URISyntaxException {

        String stats = "{\"get\":\"statistics\",\"parameters\":[],\"errors\":[],\"results\":240,\"response\":[{\"continent\":\"Asia\",\"country\":\"China\",\"population\":1439323776,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2022-04-23\", \"time\":\"2022-04-23T11:30:04+00:00\"},{\"continent\":\"Europe\",\"country\":\"Monaco\",\"population\":39743,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2022-04-23\",\"time\":\"2022-04-23T11:30:04+00:00\"}]}";

        HttpResponse<String> response = createHttpResponse(stats);

        when(client.getHttpResponse(URI.create(baseURL+"statistics"))).thenReturn(response);

        ResponseEntity<String> serviceResponseEntity = service.getStatistics(null);

        assertEquals(response.body(), serviceResponseEntity.getBody());
        assertEquals(response.statusCode(), serviceResponseEntity.getStatusCode().value());

        verify(client, times(1)).getHttpResponse(URI.create(baseURL+"statistics"));
    }

    @Test
    void whenGetCountryStatistics_thenReturnCountryStatistics() throws IOException, InterruptedException, URISyntaxException {

        String countryStats = "{\"get\":\"statistics\",\"parameters\":{...},\"errors\":[],\"results\":1,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143125,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2022-04-23\",\"time\":\"2022-04-23T11:45:03+00:00\"}]}";

        HttpResponse<String> response = createHttpResponse(countryStats);

        URIBuilder uriBuilder = new URIBuilder(baseURL + "statistics");
        uriBuilder.addParameter("country", "portugal");

        when(client.getHttpResponse(uriBuilder.build())).thenReturn(response);

        ResponseEntity<String> serviceResponseEntity = service.getStatistics("portugal");

        assertEquals(response.body(), serviceResponseEntity.getBody());
        assertEquals(response.statusCode(), serviceResponseEntity.getStatusCode().value());

        verify(client, times(1)).getHttpResponse(uriBuilder.build());
    }

    @Test
    void whenGetCountryHistory_thenReturnCountryHistory() throws IOException, InterruptedException, URISyntaxException {

        String countryHistory = "{\"get\":\"history\",\"parameters\":{\"country\":\"portugal\"},\"errors\":[],\"results\":1576,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143125,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2022-04-23\",\"time\":\"2022-04-23T12:00:03+00:00\",},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143125,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2022-04-23\",\"time\":\"2022-04-23T03:45:02+00:00\"}]}";

        HttpResponse<String> response = createHttpResponse(countryHistory);

        URIBuilder uriBuilder = new URIBuilder(baseURL + "history");
        uriBuilder.addParameter("country", "portugal");

        when(client.getHttpResponse(uriBuilder.build())).thenReturn(response);

        ResponseEntity<String> serviceResponseEntity = service.getCountryHistory("portugal", null);

        assertEquals(response.body(), serviceResponseEntity.getBody());
        assertEquals(response.statusCode(), serviceResponseEntity.getStatusCode().value());

        verify(client, times(1)).getHttpResponse(uriBuilder.build());
    }

    @Test
    void whenGetCountryDayHistory_thenReturnCountryDayHistory() throws IOException, InterruptedException, URISyntaxException {

        String countryDayHistory = "{\"get\":\"history\",\"parameters\":{...},\"errors\":[],\"results\":2,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10198931,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2020-06-02\",\"time\":\"2020-06-02T12:45:07+00:00\"}, {}]}";

        HttpResponse<String> response = createHttpResponse(countryDayHistory);

        URIBuilder uriBuilder = new URIBuilder(baseURL + "history");
        uriBuilder.addParameter("country", "portugal");
        uriBuilder.addParameter("day", "2020-06-02");


        when(client.getHttpResponse(uriBuilder.build())).thenReturn(response);

        ResponseEntity<String> serviceResponseEntity = service.getCountryHistory("portugal", "2020-06-02");

        assertEquals(response.body(), serviceResponseEntity.getBody());
        assertEquals(response.statusCode(), serviceResponseEntity.getStatusCode().value());

        verify(client, times(1)).getHttpResponse(uriBuilder.build());
    }


    private HttpResponse<String> createHttpResponse(String body) {
        return new HttpResponse<String>() {
            @Override
            public String body() {return body;}

            @Override
            public HttpHeaders headers() {return null;}

            @Override
            public Optional<HttpResponse<String>> previousResponse() {return null;}

            @Override
            public HttpRequest request() {return null;}

            @Override
            public Optional<SSLSession> sslSession() {return null;}

            @Override
            public int statusCode() {return HttpStatus.SC_OK;}

            @Override
            public URI uri() {return null;}

            @Override
            public Version version() {return null;}      
        };
    }

}
