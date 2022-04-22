package carsservice.carsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import carsservice.carsservice.dao.CarRepository;
import carsservice.carsservice.model.Car;
import carsservice.carsservice.service.CarManagerService;


@ExtendWith(MockitoExtension.class)
public class CarManagerService_UnitTest {

    @Mock(lenient = true)
    private CarRepository repository;

    @InjectMocks
    private CarManagerService service;

    @Test
    public void whenSave_thenCarShouldBeSaved() {
        Car car = new Car("tesla", "roadster");

        when(repository.save(car)).thenReturn(car);

        Car savedCar = service.save(car);

        verify(repository, VerificationModeFactory.times(1)).save(car);

        assertEquals("tesla", savedCar.getMaker());
        assertEquals("roadster", savedCar.getModel());

    }

    @Test
    public void given3Cars_whenGetAll_thenReturn3Records() {
        Car car1 = new Car("tesla", "roadster");
        Car car2 = new Car("bmw", "mini cooper");
        Car car3 = new Car("volkswagen", "touareg");

        List<Car> cars = Arrays.asList(car1, car2, car3);

        when(repository.findAll()).thenReturn(cars);

        List<Car> allCars = service.getAllCars();

        verify(repository, VerificationModeFactory.times(1)).findAll();
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains(car1.getMaker(), car2.getMaker(), car3.getMaker());  
        assertThat(allCars).extracting(Car::getModel).contains(car1.getModel(), car2.getModel(), car3.getModel());
    }


    @Test
    public void whenValidCarId_thenCarShouldBeFound() {

        Car car = new Car("volkswagen", "touareg");

        when(repository.findByCarId(car.getCarId())).thenReturn(car);
        Optional<Car> carDetails = service.getCarDetails(car.getCarId());
        verify(repository, VerificationModeFactory.times(1)).findByCarId(car.getCarId());
        assertEquals("volkswagen", carDetails.get().getMaker());
        assertEquals("touareg", carDetails.get().getModel());

    }

    @Test
    public void whenInvalidCarId_thenCarShouldNotBeFound() {

        when(repository.findByCarId(-99L)).thenReturn(null);
        Optional<Car> carDetails = service.getCarDetails(-99L);
        verify(repository, VerificationModeFactory.times(1)).findByCarId(-99L);
        assertEquals(true, carDetails.isEmpty());
    }
    
}
