package pl.sda.arppl4.hiebranterental.dao;

import pl.sda.arppl4.hiebranterental.model.Car;

import java.util.List;
import java.util.Optional;

public class CarDao implements ICarDao{
    @Override
    public void addCar(Car car) {

    }

    @Override
    public void deleteCar(Car car) {

    }

    @Override
    public Optional<Car> returnCar(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Car> returnListCars() {
        return null;
    }

    @Override
    public void updateCar(Car car) {

    }
}
