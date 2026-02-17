package vehicle;

import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;

public interface Vehicle {
    void setBrand(String brand);
    String getBrand();

    void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    String[] getModelNames();
    double getPriceByName(String modelName) throws NoSuchModelNameException;
    void setPriceByName(String modelName, double price) throws NoSuchModelNameException;
    double[] getModelPrices();
    void addModel(String modelName, double price) throws DuplicateModelNameException;
    void removeModel(String modelName) throws NoSuchModelNameException;
}
