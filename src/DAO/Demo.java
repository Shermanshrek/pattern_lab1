package DAO;

import vehicle.Auto;
import vehicle.Motorcycle;
import vehicle.Vehicle;

public class Demo {
    public static void main(String[] args) {
        try {
            // Создаём автомобиль
            Auto auto = new Auto("Toyota");
            auto.addModel("Camry", 25000);
            auto.addModel("Corolla", 18000);

            // Создаём мотоцикл
            Motorcycle motorcycle = new Motorcycle("Harley-Davidson");
            motorcycle.addModel("Sportster", 12000);
            motorcycle.addModel("Iron 883", 9500);

            // Сохраняем в текстовые файлы
            VehicleDAO textDAO = new TextVehicleDAO();
            textDAO.write(auto, "auto.txt");
            textDAO.write(motorcycle, "moto.txt");

            // Сохраняем в бинарные файлы
            VehicleDAO serialDAO = new SerializedVehicleDAO();
            serialDAO.write(auto, "auto.ser");
            serialDAO.write(motorcycle, "moto.ser");

            // Читаем обратно
            Vehicle autoFromText = textDAO.read("auto.txt");
            Vehicle motoFromText = textDAO.read("moto.txt");
            Vehicle autoFromSer = serialDAO.read("auto.ser");
            Vehicle motoFromSer = serialDAO.read("moto.ser");

            // Выводим для проверки
            System.out.println("Автомобиль из текста:");
            printVehicle(autoFromText);
            System.out.println("Мотоцикл из текста:");
            printVehicle(motoFromText);
            System.out.println("Автомобиль из сериализации:");
            printVehicle(autoFromSer);
            System.out.println("Мотоцикл из сериализации:");
            printVehicle(motoFromSer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printVehicle(Vehicle v) {
        System.out.println("Бренд: " + v.getBrand());
        String[] names = v.getModelNames();
        double[] prices = v.getModelPrices();
        for (int i = 0; i < names.length; i++) {
            System.out.println("  " + names[i] + " - " + prices[i]);
        }
    }
}

