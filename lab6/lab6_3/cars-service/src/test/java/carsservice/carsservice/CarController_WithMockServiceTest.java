package carsservice.carsservice;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import carsservice.carsservice.controller.CarController;
import carsservice.carsservice.model.Car;
import carsservice.carsservice.service.CarManagerService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@WebMvcTest(CarController.class)
public class CarController_WithMockServiceTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private CarManagerService service;

    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car car = new Car();

        when(service.save(Mockito.any())).thenReturn(car);

        mvc.perform(
            post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JSONUtils.toJson(car)))
            .andExpect(status().isCreated());

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    public void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car car1 = new Car("tesla", "roadster");
        Car car2 = new Car("bmw", "mini cooper");
        Car car3 = new Car("volkswagen", "touareg");

        List<Car> cars = Arrays.asList(car1, car2, car3);

        when(service.getAllCars()).thenReturn(cars);

        mvc.perform(
            get("/api/cars").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].maker", is(car1.getMaker())))
            .andExpect(jsonPath("$[0].model", is(car1.getModel())))
            .andExpect(jsonPath("$[1].maker", is(car2.getMaker())))
            .andExpect(jsonPath("$[1].model", is(car2.getModel())))
            .andExpect(jsonPath("$[2].maker", is(car3.getMaker())))
            .andExpect(jsonPath("$[2].model", is(car3.getModel())));

        verify(service, times(1)).getAllCars();
    }

    @Test
    public void whenGetCarById_thenReturnJson() throws Exception {

        Car car = new Car("volkswagen", "touareg");

        when(service.getCarDetails(1L)).thenReturn(Optional.of(car));

        mvc.perform(
            get("/api/cars/1").contentType(MediaType.APPLICATION_JSON).content(JSONUtils.toJson(car)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker", is(car.getMaker())))
            .andExpect(jsonPath("$.model", is(car.getModel())));
        
        verify(service, times(1)).getCarDetails(1L);
    }
}
