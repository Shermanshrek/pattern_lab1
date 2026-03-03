package ChainOfResponsibility;

import vehicle.Vehicle;

import java.io.FileNotFoundException;

public interface VehicleChain {
    void printVehicle(Vehicle vehicle, String filename) throws FileNotFoundException;

    void setNext(VehicleChain next);
}
