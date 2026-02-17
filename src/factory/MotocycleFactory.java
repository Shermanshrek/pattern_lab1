package factory;

import exceptions.DuplicateModelNameException;
import vehicle.Motocycle;
import vehicle.Vehicle;

public class MotocycleFactory implements TransportFactory{
    @Override
    public Vehicle createInstance(String brand, int length) throws DuplicateModelNameException {
        return new Motocycle(brand, length);
    }
}
