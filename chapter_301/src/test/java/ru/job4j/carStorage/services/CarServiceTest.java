package ru.job4j.carStorage.services;

import org.junit.Test;
import ru.job4j.carStorage.models.Car;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarServiceTest {

    private final CarService cars = CarService.getInstance();

    @Test
    public void testSave() throws Exception {
        Car car = new Car();
        car.setBrand("test");
        car.setModel("test");
        car = this.cars.save(car);
        assertThat(car, is(this.cars.findById(car.getId())));
    }

    @Test
    public void testGetAll() throws Exception {
        Car car = new Car();
        car.setBrand("test");
        car.setModel("test");
        this.cars.save(car);
        assertTrue(this.cars.getAll().contains(car));
    }

    @Test
    public void testUpdate() throws Exception {
        Car car = new Car();
        car.setBrand("test");
        car.setModel("test");
        car = this.cars.save(car);
        car.setBrand("testNew");
        this.cars.update(car);
        assertThat(car, is(this.cars.findById(car.getId())));
    }

    @Test
    public void testFindForLastDay() throws Exception {
        Date currentDate = new Date();

        LocalDateTime currentLocalDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localDateTime;
        // minus two
        localDateTime = currentLocalDateTime.minusDays(2);
        Date currentDateMinusTwoDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        // minus six house
        localDateTime = currentLocalDateTime.minusHours(6);
        Date currentDateMinusSixHouse = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        // minus twentythree house
        localDateTime = currentLocalDateTime.minusHours(23);
        Date currentDateMinusTwentyThreeHouse = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Car car1 = new Car();
        car1.setBrand("test_1");
        car1.setCreated(new Timestamp(currentDateMinusSixHouse.getTime()));
        this.cars.save(car1);

        Car car2 = new Car();
        car2.setBrand("test_2");
        car2.setCreated(new Timestamp(currentDateMinusTwoDay.getTime()));
        this.cars.save(car2);

        Car car3 = new Car();
        car3.setBrand("test_3");
        car3.setCreated(new Timestamp(currentDateMinusTwentyThreeHouse.getTime()));
        this.cars.save(car3);

        List<Car> listCars = new ArrayList();
        listCars.add(car1);
        listCars.add(car3);
        assertThat(listCars, is(this.cars.findForLastDay()));
    }

    @Test
    public void testGetListBrand() throws Exception {
        Car car1 = new Car();
        car1.setBrand("test_1");
        this.cars.save(car1);

        Car car2 = new Car();
        car2.setBrand("test_2");
        this.cars.save(car2);

        Car car3 = new Car();
        car3.setBrand("test_1");
        this.cars.save(car3);

        List<String> listBrands = new ArrayList();
        listBrands.add("test_1");
        listBrands.add("test_2");
        assertThat(listBrands, is(this.cars.getListBrand()));
    }

    @Test
    public void testFindWithSpecificBrand() throws Exception {
        Car car1 = new Car();
        car1.setBrand("test_1");
        this.cars.save(car1);

        Car car2 = new Car();
        car2.setBrand("test_2");
        this.cars.save(car2);

        Car car3 = new Car();
        car3.setBrand("test_1");
        this.cars.save(car3);

        List<Car> listCars = new ArrayList();
        listCars.add(car1);
        listCars.add(car3);
        assertThat(listCars, is(this.cars.findWithSpecificBrand("test_1")));
    }
}