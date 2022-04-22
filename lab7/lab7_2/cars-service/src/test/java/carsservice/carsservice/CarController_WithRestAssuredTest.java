package carsservice.carsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import carsservice.carsservice.controller.CarController;
import carsservice.carsservice.model.Car;
import carsservice.carsservice.service.CarManagerService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(CarController.class)
public class CarController_WithRestAssuredTest {

    @MockBean
    CarManagerService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car car = new Car();

        when(service.save(car)).thenReturn(car);

        RestAssuredMockMvc.mockMvc(mockMvc);

        RestAssuredMockMvc.given()
            .header("Content-type", "application/json")
            .and().body(car)
            .and().post("/api/cars")
            .then().statusCode(201);
    }

    @Test
    public void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car car1 = new Car("tesla", "roadster");
        Car car2 = new Car("bmw", "mini cooper");
        Car car3 = new Car("volkswagen", "touareg");

        List<Car> cars = Arrays.asList(car1, car2, car3);

        when(service.getAllCars()).thenReturn(cars);

        RestAssuredMockMvc.mockMvc(mockMvc);

        RestAssuredMockMvc.given()
            .header("Content-type", "application/json")
            .and().body(cars)
            .and().get("/api/cars")
            .then().statusCode(200)
            .and().body("maker", hasItems(car1.getMaker(), car2.getMaker(), car3.getMaker()))
            .and().body("model",  hasItems(car1.getModel(), car2.getModel(), car3.getModel()));
    }

    @Test
    public void whenGetCarById_thenReturnJson() throws Exception {

        Car car = new Car("volkswagen", "touareg");

        when(service.getCarDetails(1L)).thenReturn(Optional.of(car));

        RestAssuredMockMvc.mockMvc(mockMvc);

        RestAssuredMockMvc.given()
            .header("Content-type", "application/json")
            .and().body(car)
            .and().get("/api/cars/1")
            .then().statusCode(200)
            .and().body("maker", equalTo(car.getMaker()))
            .and().body("model",  equalTo(car.getModel()));
    }

}