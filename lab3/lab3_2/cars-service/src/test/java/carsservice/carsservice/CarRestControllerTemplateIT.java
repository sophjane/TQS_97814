package carsservice.carsservice;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import carsservice.carsservice.dao.CarRepository;
import carsservice.carsservice.model.Car;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = "application-integrationtest.properties")
@AutoConfigureTestDatabase
public class CarRestControllerTemplateIT {

    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar()  {

        Car car = new Car("volkswagen", "touareg");

        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", car, Car.class);
        
        List<Car> found = repository.findAll();

        assertThat(found).extracting(Car::getMaker).containsOnly("volkswagen");
        assertThat(found).extracting(Car::getModel).containsOnly("touareg");
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200() {
        
        Car car1 = new Car("tesla", "roadster");
        Car car2 = new Car("bmw", "mini cooper");
        repository.saveAndFlush(car1);
        repository.saveAndFlush(car2);


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("tesla", "bmw");
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("roadster", "mini cooper");
    }

    @Test
    public void givenCar_whenGetCarById_thenStatus200() {

        Car car = new Car("volkswagen", "touareg");
        repository.saveAndFlush(car);

        ResponseEntity<Car> response = restTemplate.exchange("/api/cars/1", HttpMethod.GET, null, Car.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals("volkswagen", response.getBody().getMaker());
        assertEquals("touareg", response.getBody().getModel());
    }
    
}
