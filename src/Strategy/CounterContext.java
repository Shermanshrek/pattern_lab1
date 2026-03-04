package Strategy;

import java.io.*;
import java.util.Map;

public class CounterContext {
    private FrequencyCounter strategy;

    public CounterContext(FrequencyCounter strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(FrequencyCounter strategy) {
        this.strategy = strategy;
    }

    public Map<Integer, Integer> executeCount(int[] a) {
        return strategy.count(a);
    }

    public static void main(String[] args) {
        // сначала создаем файл, потом уже вызывает остальной код
        /* try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("array.ser"))) {
            oos.writeObject(new int[]{3, 1, 2, 1, 3, 3});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */
        if (args.length != 1) {
            System.out.println("Укажите имя файла");
            return;
        }
        int[] array = readArrayFromFile(args[0]);
        if (array == null) return;

        CounterContext context = new CounterContext(new HashMapCounter());
        System.out.println("HashMapCounter:");
        printFrequency(context.executeCount(array));

        System.out.println("SortCounter:");
        context.setStrategy(new SortCounter());
        printFrequency(context.executeCount(array));
    }

    private static int[] readArrayFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (int[]) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден: " + e.getMessage());
        }
        return null;
    }

    private static void printFrequency(Map<Integer, Integer> freq) {
        freq.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}
