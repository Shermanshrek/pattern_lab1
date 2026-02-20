import config.ConfigLoader;
import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;
import factory.MotocycleFactory;
import utils.VehicleUtils;
import vehicle.Auto;
import vehicle.Motocycle;
import vehicle.Vehicle;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {
        ConfigLoader configLoader1 = ConfigLoader.getInstance();
        ConfigLoader configLoader2 = ConfigLoader.getInstance();
        System.out.println(configLoader1.getProperties());
        if (configLoader1.equals(configLoader2)) {
            System.out.println("true");
        }
        else System.out.println("false");
        testAuto();
        System.out.println("==========MOTOCYCLE==========");
        testMoto();

        Vehicle fact1 = VehicleUtils.createInstance("bmw", 2);
        System.out.println(fact1.getClass());
        VehicleUtils.setFactory(new MotocycleFactory());
        Vehicle fact2 = VehicleUtils.createInstance("bmw", 2);
        System.out.println(fact2.getClass());
    }

    private static void testAuto() throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {
        Auto auto = new Auto("Lada", 3);
        System.out.println(Arrays.toString(auto.getModelNames()));

        // Изменение бренда
        auto.setBrand("VAZ");
        System.out.println("Бренд изменён на: " + auto.getBrand());

        // Изменение имени модели
        auto.setModelName("Lada0", "Granta");
        System.out.println("Имя модели 'Lada0' изменено на 'Granta'");

        // Изменение цены модели
        auto.setPriceByName("Lada1", 500.0);
        System.out.println("Цена модели 'Lada1' изменена на 500.0");

        // Добавление новой модели
        auto.addModel("Vesta", 800.0);
        System.out.println("Добавлена модель 'Vesta' с ценой 800.0");

        // Удаление модели
        auto.removeModel("Lada2");
        System.out.println("Удалена модель 'Lada2'");
        System.out.println(Arrays.toString(auto.getModelNames()));

        Auto clonedAuto = auto.clone();
        System.out.println("\n--- Клонирование Auto ---");
        System.out.println("Оригинал до изменений в клоне:");
        printAuto(auto);
        System.out.println("Клон до изменений:");
        printAuto(clonedAuto);

        // меняем клона
        clonedAuto.setBrand("BMW");
        clonedAuto.setModelName("Granta", "X5");

        System.out.println("\nПосле изменений в клоне:");
        System.out.println("Оригинал (не должен измениться):");
        printAuto(auto);
        System.out.println("Клон (изменён):");
        printAuto(clonedAuto);
    }

    private static void printAuto(Auto auto) {
        System.out.println("Бренд: " + auto.getBrand());
        System.out.println("Модели: " + Arrays.toString(auto.getModelNames()));
        System.out.println("Цены: " + Arrays.toString(auto.getModelPrices()));
        System.out.println("Количество моделей: " + auto.getSize());
    }

    private static void printMotocycle(Motocycle moto) {
        System.out.println("Бренд: " + moto.getBrand());
        System.out.println("Модели: " + Arrays.toString(moto.getModelNames()));
        System.out.println("Цены: " + Arrays.toString(moto.getModelPrices()));
        System.out.println("Количество моделей: " + moto.getModelNames().length);
    }

    private static void testMoto() throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {
        Motocycle moto = new Motocycle("Yamaha", 3);
        printMotocycle(moto);

        // Изменение бренда
        moto.setBrand("Honda");
        System.out.println("Бренд изменён на: " + moto.getBrand());

        // Изменение имени модели
        moto.setModelName("Yamaha0", "CBR");
        System.out.println("Имя модели 'Yamaha0' изменено на 'CBR'");

        // Изменение цены модели
        moto.setPriceByName("Yamaha1", 7500.0);
        System.out.println("Цена модели 'Yamaha1' изменена на 7500.0");

        // Добавление новой модели
        moto.addModel("Africa Twin", 12000.0);
        System.out.println("Добавлена модель 'Africa Twin' с ценой 12000.0");

        // Удаление модели
        moto.removeModel("Yamaha2");
        System.out.println("Удалена модель 'Yamaha2'");

        printMotocycle(moto);

        Motocycle clonedMoto = moto.clone();
        System.out.println("\n--- Клонирование Motocycle ---");
        System.out.println("Оригинал до изменений в клоне:");
        printMotocycle(moto);
        System.out.println("Клон до изменений:");
        printMotocycle(clonedMoto);

        // меняем клона
        clonedMoto.setBrand("Suzuki");
        clonedMoto.setModelName("CBR", "GSX-R");
        clonedMoto.setPriceByName("Yamaha1", 8000.0);
        clonedMoto.addModel("Hayabusa", 18000.0);
        clonedMoto.removeModel("Africa Twin");

        System.out.println("\nПосле изменений в клоне:");
        System.out.println("Оригинал (не должен измениться):");
        printMotocycle(moto);
        System.out.println("Клон (изменён):");
        printMotocycle(clonedMoto);
    }

}