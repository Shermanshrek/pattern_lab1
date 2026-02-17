package vehicle;

import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;

public class SynchronizedVehicle implements Vehicle {

    private final Vehicle vehicle;

    public SynchronizedVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public synchronized void setBrand(String brand) {
        vehicle.setBrand(brand);
    }

    @Override
    public synchronized String getBrand() {
        return vehicle.getBrand();
    }

    @Override
    public synchronized void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        vehicle.setModelName(oldName, newName);
    }

    @Override
    public synchronized String[] getModelNames() {
        return vehicle.getModelNames();
    }

    @Override
    public synchronized double getPriceByName(String modelName) throws NoSuchModelNameException {
        return vehicle.getPriceByName(modelName);
    }

    @Override
    public synchronized void setPriceByName(String modelName, double price) throws NoSuchModelNameException {
        vehicle.setPriceByName(modelName, price);
    }

    @Override
    public synchronized double[] getModelPrices() {
        return vehicle.getModelPrices();
    }

    @Override
    public synchronized void addModel(String modelName, double price) throws DuplicateModelNameException {
        vehicle.addModel(modelName, price);
    }

    @Override
    public synchronized void removeModel(String modelName) throws NoSuchModelNameException {
        vehicle.removeModel(modelName);
    }
}
