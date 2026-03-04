import ChainOfResponsibility.PrintInColumn;
import ChainOfResponsibility.PrintInLine;
import Command.ColumnCommand;
import Command.OneLineCommand;
import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;
import vehicle.Auto;
import vehicle.Motorcycle;
import vehicle.Vehicle;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException, IOException {
        PrintInLine printInLine = new PrintInLine();
        PrintInColumn printInColumn = new PrintInColumn();

        printInLine.setNext(printInColumn);
        printInColumn.setNext(printInLine);

        // Chain os Responsibility
        try{
            Vehicle autoSmall = new Auto("Toyota", 2);
            printInLine.printVehicle(autoSmall, "auto_small.txt");
            System.out.println("Файл auto_small.txt создан.");

            Vehicle autoLarge = new Auto("Honda", 5);
            printInLine.printVehicle(autoLarge, "auto_large.txt");
            System.out.println("Файл auto_large.txt создан.");

            Vehicle moto = new Motorcycle("Yamaha", 4);
            printInColumn.printVehicle(moto, "moto.txt");
            System.out.println("Файл moto.txt создан.");
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }

        //Command
        try{
            Auto auto = new Auto("Toyota", 3);
            auto.setPrintCommand(new OneLineCommand());
            try(PrintWriter pw = new PrintWriter("auto_command_line.txt")){
                auto.print(pw);
                System.out.println("auto_command_line.txt создан.");
            }
            auto.setPrintCommand(new ColumnCommand());
            try(PrintWriter pw = new PrintWriter("auto_command_column.txt")){
                auto.print(pw);
                System.out.println("auto_command_column.txt создан.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}