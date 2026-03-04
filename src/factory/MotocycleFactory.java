package factory;

import exceptions.DuplicateModelNameException;
import vehicle.Motorcycle;
import vehicle.Vehicle;

public class MotocycleFactory implements TransportFactory{
    @Override
    public Vehicle createInstance(String brand, int length) throws DuplicateModelNameException {
        return new Motorcycle(brand, length);
    }
}
