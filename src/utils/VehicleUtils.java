package utils;

import exceptions.DuplicateModelNameException;
import factory.AutoFactory;
import factory.TransportFactory;
import vehicle.Vehicle;

import java.util.Arrays;

public class VehicleUtils {
    private static TransportFactory factory;

    static {
        factory = new AutoFactory();
    }

    public static void setFactory(TransportFactory factory) {
        VehicleUtils.factory = factory;
    }

    public static double meanPrice(Vehicle vehicle) {
        double[] prices = vehicle.getModelPrices();
        double sum = 0.0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    public static void printModels(Vehicle vehicle) {
        String[] models = vehicle.getModelNames();
        System.out.println(Arrays.toString(models));
    }

    public static void printPrices(Vehicle vehicle) {
        double[] prices = vehicle.getModelPrices();
        System.out.println(Arrays.toString(prices));
    }

    public static Vehicle createInstance(String name, int size) throws DuplicateModelNameException {
        return factory.createInstance(name, size);
    }
}
