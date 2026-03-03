package Command;

import vehicle.Auto;

import java.io.PrintWriter;

public class OneLineCommand implements Command {

    @Override
    public void writeInFile(PrintWriter pw, Auto auto) {
        pw.print("Brand: " + auto.getBrand() + "; ");
        pw.print("Models: ");
        for (String name : auto.getModelNames()) {
            pw.print(name + " ");
        }
        pw.print("; Prices: ");
        for (double price : auto.getModelPrices()) {
            pw.print(price + " ");
        }
    }
}
