package DAO;

import vehicle.Vehicle;
import java.io.*;

public class SerializedVehicleDAO implements VehicleDAO {

    @Override
    public void write(Vehicle vehicle, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(vehicle);
        }
    }

    @Override
    public Vehicle read(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Vehicle) ois.readObject();
        }
    }
}
