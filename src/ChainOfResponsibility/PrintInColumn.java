package ChainOfResponsibility;

import vehicle.Vehicle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintInColumn implements VehicleChain {
    private VehicleChain next;
    @Override
    public void printVehicle(Vehicle vehicle, String filename) throws FileNotFoundException {
        if (vehicle.getModelNames().length > 3) {
            try(PrintWriter pw = new PrintWriter(filename)) {
                pw.println("Brand: " + vehicle.getBrand());
                double[] prices = vehicle.getModelPrices();
                String[] names = vehicle.getModelNames();
                for (int i = 0; i < names.length; i++) {
                    pw.println("Model: " + names[i] + " Price: " + prices[i]);
                }
            }
        } else{
            if (next != null) {
                next.printVehicle(vehicle, filename);
            } else {
                throw new RuntimeException("No handler found for vehicle with "
                        + vehicle.getModelNames().length + " models");
            }
        }
    }

    @Override
    public void setNext(VehicleChain next) {
        this.next = next;
    }
}
