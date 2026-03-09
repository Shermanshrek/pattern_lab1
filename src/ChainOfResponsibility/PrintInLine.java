package ChainOfResponsibility;

import utils.VehicleUtils;
import vehicle.Vehicle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintInLine implements VehicleChain {
    private VehicleChain next;

    @Override
    public void printVehicle(Vehicle vehicle, String filename) throws FileNotFoundException {
        if (vehicle.getModelNames().length <= 3) {
            try(PrintWriter writer = new PrintWriter(filename)) {
                VehicleUtils.writeInline(vehicle, writer);
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
