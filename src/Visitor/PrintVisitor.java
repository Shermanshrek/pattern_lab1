package Visitor;

import exceptions.DuplicateModelNameException;
import vehicle.Auto;
import vehicle.Motorcycle;
import vehicle.Vehicle;

public class PrintVisitor implements Visitor {

    @Override
    public void visit(Auto vehicle) {
        System.out.println("Автомобиль " + vehicle.getBrand() + ": ");
        String[] names = vehicle.getModelNames();
        double[] prices = vehicle.getModelPrices();
        for (int i = 0; i < names.length; i++) {
            System.out.print(names[i] + " (" + prices[i] + ")");
            if (i < names.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    @Override
    public void visit(Motorcycle vehicle) {
        System.out.println("Мотоцикл " + vehicle.getBrand() + ":");
        String[] names = vehicle.getModelNames();
        double[] prices = vehicle.getModelPrices();
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + " (" + prices[i] + ")");
        }
    }

    public static void main(String[] args) throws DuplicateModelNameException {
        Auto auto = new Auto("Toyota", 2);
        try {
            auto.addModel("Camry", 25000);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }

        Motorcycle motorcycle = new Motorcycle("Harley-Davidson", 1);
        motorcycle.addModel("Sportster", 12000);

        PrintVisitor printer = new PrintVisitor();

        System.out.println("--- Вывод через Visitor ---");
        auto.accept(printer);
        motorcycle.accept(printer);
    }
}
