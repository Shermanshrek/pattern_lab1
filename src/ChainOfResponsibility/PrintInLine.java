package ChainOfResponsibility;

import vehicle.Vehicle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintInLine implements VehicleChain {
    private VehicleChain next;

    @Override
    public void printVehicle(Vehicle vehicle, String filename) throws FileNotFoundException {
        if (vehicle.getModelNames().length <= 3) {
            try(PrintWriter writer = new PrintWriter(filename)) {
                writer.print("Brand: " + vehicle.getBrand() + "; ");
                writer.print("Models: ");
                for (String name : vehicle.getModelNames()) {
                    writer.print(name + " ");
                }
                writer.print("; Prices: ");
                for (double price : vehicle.getModelPrices()){
                    writer.print(price + " ");
                }
            }
        } else {
            if (next != null){
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
