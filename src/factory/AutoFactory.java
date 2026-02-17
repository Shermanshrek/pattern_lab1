package factory;

import vehicle.Auto;
import vehicle.Vehicle;

public class AutoFactory implements TransportFactory{
    @Override
    public Vehicle createInstance(String brand, int length) {
        return new Auto(brand, length);
    }
}
