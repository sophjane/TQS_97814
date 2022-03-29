package carsservice.carsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import carsservice.carsservice.dao.CarRepository;
import carsservice.carsservice.model.Car;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repository;

    @Test
    public void whenFindCarById_thenReturnCar() {
        
        Car car = new Car("volkswagen", "touareg");

        entityManager.persistAndFlush(car);

        Car fromDb = repository.findByCarId(car.getCarId());

        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getMaker()).isEqualTo(car.getMaker());
        assertThat(fromDb.getModel()).isEqualTo(car.getModel());
    }

    @Test
    public void whenInvalidCarId_thenReturnNull() {
        Car car = repository.findByCarId(-99L);
        assertThat(car).isNull();
    }


    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
    
        Car car1 = new Car("tesla", "roadster");
        Car car2 = new Car("bmw", "mini cooper");
        Car car3 = new Car("volkswagen", "touareg");

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.flush();

        List<Car> allCars = repository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getMaker).containsOnly(car1.getMaker(), car2.getMaker(), car3.getMaker());
        assertThat(allCars).extracting(Car::getModel).containsOnly(car1.getModel(), car2.getModel(), car3.getModel());
    }

    
}
