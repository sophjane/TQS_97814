package carsservice.carsservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import carsservice.carsservice.dao.CarRepository;
import carsservice.carsservice.model.Car;

@Service
public class CarManagerService {

    @Autowired
    public CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId) {
        Car car = carRepository.findByCarId(carId);

        if(car != null) {
            return Optional.of(car);
        } else {

            return Optional.empty();
        }
    }

}
