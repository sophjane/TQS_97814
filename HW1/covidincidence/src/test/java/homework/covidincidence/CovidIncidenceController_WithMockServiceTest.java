package homework.covidincidence;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;

import homework.covidincidence.controller.CovidIncidenceController;
import homework.covidincidence.service.CovidIncidenceService;

@WebMvcTest(CovidIncidenceController.class)
class CovidIncidenceController_WithMockServiceTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CovidIncidenceService service;


    @Test
    void givenCountries_whenGetCountries_thenReturnListOfCountries() throws Exception {

        String countries = "{\"get\":\"countries\",\"parameters\":[],\"errors\":[],\"results\":233,\"response\":[\"Afghanistan\",\"Albania\"]}";

        ResponseEntity<String> response = new ResponseEntity<>(countries, HttpStatus.OK);

        when(service.getCountries()).thenReturn(response);
        mvc.perform(
            get("/api/countries").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());


        verify(service, times(1)).getCountries();
    }  

    @Test
    void givenStatistics_whenGetStatistics_thenReturnStatistics() throws Exception {

        String stats = "{\"get\":\"statistics\",\"parameters\":[],\"errors\":[],\"results\":240,\"response\":[{\"continent\":\"Asia\",\"country\":\"China\",\"population\":1439323776,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2022-04-23\", \"time\":\"2022-04-23T11:30:04+00:00\"},{\"continent\":\"Europe\",\"country\":\"Monaco\",\"population\":39743,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2022-04-23\",\"time\":\"2022-04-23T11:30:04+00:00\"}]}";

        ResponseEntity<String> response = new ResponseEntity<>(stats, HttpStatus.OK);

        when(service.getStatistics(null)).thenReturn(response);
        mvc.perform(
            get("/api/statistics").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(service, times(1)).getStatistics(null);
    }

    @Test
    void givenCountryStatistics_whenGetCountryStatistics_thenReturnCountryStatistics() throws Exception {

        String countryStats = "{\"get\":\"statistics\",\"parameters\":{...},\"errors\":[],\"results\":1,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143125,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2022-04-23\",\"time\":\"2022-04-23T11:45:03+00:00\"}]}";

        ResponseEntity<String> response = new ResponseEntity<>(countryStats, HttpStatus.OK);

        when(service.getStatistics("portugal")).thenReturn(response);
        mvc.perform(
            get("/api/statistics?country=portugal").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(service, times(1)).getStatistics("portugal");
    }

    @Test
    void givenCountryHistory_whenGetCountryHistory_thenReturnCountryHistory() throws Exception {

        String countryHistory = "{\"get\":\"history\",\"parameters\":{\"country\":\"portugal\"},\"errors\":[],\"results\":1576,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143125,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2022-04-23\",\"time\":\"2022-04-23T12:00:03+00:00\",},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143125,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2022-04-23\",\"time\":\"2022-04-23T03:45:02+00:00\"}]}";

        ResponseEntity<String> response = new ResponseEntity<>(countryHistory, HttpStatus.OK);

        when(service.getCountryHistory("portugal", null)).thenReturn(response);
        mvc.perform(
            get("/api/history?country=portugal").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(service, times(1)).getCountryHistory("portugal", null);
    }

    @Test
    void givenCountryDayHistory_whenGetCountryDayeHistory_thenReturnCountryDayHistory() throws Exception {

        String countryDayHistory = "{\"get\":\"history\",\"parameters\":{...},\"errors\":[],\"results\":2,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10198931,\"cases\":{...},\"deaths\":{...},\"tests\":{...},\"day\":\"2020-06-02\",\"time\":\"2020-06-02T12:45:07+00:00\"}, {}]}";

        ResponseEntity<String> response = new ResponseEntity<>(countryDayHistory, HttpStatus.OK);

        when(service.getCountryHistory("portugal", "2020-06-02")).thenReturn(response);
        mvc.perform(
            get("/api/history?country=portugal&day=2020-06-02").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(service, times(1)).getCountryHistory("portugal", "2020-06-02");
    }

    @Test
    void givenCacheUsageStats_whenGetCacheUsageStats_thenReturnCacheUsageStats() throws Exception {
        String cacheInfo = "This cache contains 2 elements with 5 of TTL.\nNº hits: 2, nº misses: 5";

        JSONObject json = new JSONObject();
        json.put("response", cacheInfo);

        ResponseEntity<String> response = new ResponseEntity<>(json.toString(), HttpStatus.OK);

        when(service.getCacheUsageStats()).thenReturn(response);
        mvc.perform(
            get("/api/cache").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(service, times(1)).getCacheUsageStats();
    }
}
