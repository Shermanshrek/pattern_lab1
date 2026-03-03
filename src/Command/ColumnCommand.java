package Command;

import vehicle.Auto;

import java.io.PrintWriter;

public class ColumnCommand implements Command {
    @Override
    public void writeInFile(PrintWriter pw, Auto auto) throws Exception {
        pw.println("Brand: " + auto.getBrand());
        String[] names = auto.getModelNames();
        double[] prices = auto.getModelPrices();
        for (int i = 0; i < names.length; i++) {
            pw.println("Model: " + names[i] + ", price: " + prices[i]);
        }
    }
}

