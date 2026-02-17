package factory;

import exceptions.DuplicateModelNameException;
import vehicle.Vehicle;

public interface TransportFactory {
    public Vehicle createInstance(String brand, int length) throws DuplicateModelNameException;
}
