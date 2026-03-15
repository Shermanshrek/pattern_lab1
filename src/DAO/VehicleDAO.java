package DAO;

import vehicle.Vehicle;
import java.io.IOException;

public interface VehicleDAO {
    void write(Vehicle vehicle, String fileName) throws IOException;
    Vehicle read(String fileName) throws IOException, ClassNotFoundException;
}
