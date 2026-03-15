package DAO;

import vehicle.*;
import exceptions.DuplicateModelNameException;
import exceptions.ModelPriceOutOfBoundsException;
import java.io.*;

public class TextVehicleDAO implements VehicleDAO {

    @Override
    public void write(Vehicle vehicle, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Сохраняем полное имя класса для правильного восстановления
            writer.println(vehicle.getClass().getName());
            writer.println(vehicle.getBrand());
            String[] names = vehicle.getModelNames();
            double[] prices = vehicle.getModelPrices();
            writer.println(names.length);
            for (int i = 0; i < names.length; i++) {
                writer.println(names[i] + " " + prices[i]);
            }
        }
    }

    @Override
    public Vehicle read(String fileName) throws IOException, ClassNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String className = reader.readLine();
            if (className == null) throw new IOException("Empty file");
            String brand = reader.readLine();
            if (brand == null) throw new IOException("Missing brand");
            String countLine = reader.readLine();
            if (countLine == null) throw new IOException("Missing count");
            int count = Integer.parseInt(countLine);

            Class<?> clazz = Class.forName(className);
            Vehicle vehicle;
            try {
                vehicle = (Vehicle) clazz.getConstructor(String.class).newInstance(brand);
            } catch (Exception e) {
                throw new IOException("Cannot create instance of " + className, e);
            }

            for (int i = 0; i < count; i++) {
                String line = reader.readLine();
                if (line == null) throw new IOException("Not enough models");

                // Ищем последний пробел, отделяющий цену
                int lastSpace = line.lastIndexOf(' ');
                if (lastSpace == -1) {
                    throw new IOException("Invalid model line: " + line);
                }
                String name = line.substring(0, lastSpace);
                double price;
                try {
                    price = Double.parseDouble(line.substring(lastSpace + 1));
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid price in line: " + line);
                }

                try {
                    vehicle.addModel(name, price);
                } catch (DuplicateModelNameException | ModelPriceOutOfBoundsException e) {
                    throw new IOException("Error adding model: " + e.getMessage());
                }
            }
            return vehicle;
        }
    }
}
