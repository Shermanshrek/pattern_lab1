package utils;

import exceptions.DuplicateModelNameException;
import factory.AutoFactory;
import factory.TransportFactory;
import vehicle.Auto;
import vehicle.SynchronizedVehicle;
import vehicle.Vehicle;

import java.io.PrintWriter;
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

    public static SynchronizedVehicle synchronizeVehicle(Vehicle vehicle) {
        return new SynchronizedVehicle(vehicle);
    }

    public static void writeInline(Vehicle vehicle, PrintWriter writer) {
        writer.print("Brand: " + vehicle.getBrand() + " | ");
        writer.print("Models: ");
        String[] names = vehicle.getModelNames();
        double[] prices = vehicle.getModelPrices();
        for (int i = 0; i < names.length; i++) {
            writer.print(names[i] + " (" + prices[i] + ")");
            if (i < names.length - 1) {
                writer.print(" | ");
            }
        }
    }

    public static void writeColumn(Vehicle vehicle, PrintWriter writer) {
        writer.println("Brand: " + vehicle.getBrand());
        String[] names = vehicle.getModelNames();
        double[] prices = vehicle.getModelPrices();
        for (int i = 0; i < names.length; i++) {
            writer.println("Model: " + names[i] + ", price: " + prices[i]);
        }
    }
}
