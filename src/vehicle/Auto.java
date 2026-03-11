package vehicle;

import Command.Command;
import Visitor.Visitor;
import exceptions.DuplicateModelNameException;
import exceptions.ModelPriceOutOfBoundsException;
import exceptions.NoSuchModelNameException;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Auto implements Vehicle, Cloneable, Serializable {
    private String brand;
    private Model[] models;

    public Auto(String brand, int size) {
        this.brand = brand;
        models = new Model[size];
        for (int i = 0; i < size; i++) {
            models[i] = new Model(this.brand + i, 1 + i);
        }
    }

    @Override
    protected Auto clone() throws CloneNotSupportedException {
        Auto res = null;
        try {
            res = (Auto) super.clone();
            res.models = models.clone();
            for (int i = 0; i < getSize(); i++) {
                res.models[i] = models[i].clone();
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    static class Model implements Cloneable, Serializable {
        private String modelName;
        private double price;

        public Model(String modelName, double price) {
            this.modelName = modelName;
            this.price = price;
        }

        @Override
        protected Model clone() throws CloneNotSupportedException {
            Model md = null;
            try {
                md = (Model) super.clone();
            } catch (CloneNotSupportedException ex) {
                System.out.println("Cloning goes wrong...");
            }
            return md;
        }

        @Override
        public String toString() {
            return String.format("%s: %s", modelName, price);
        }
    }

    private class AutoIterator implements Iterator<Model> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < models.length;
        }

        @Override
        public Model next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return models[currentIndex++];
        }
    }

    public static class Memento {
        private byte[] state;

        public void setAuto(Auto auto) {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(auto);
                state = baos.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public Auto getAuto() {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(state);
                 ObjectInputStream ois = new ObjectInputStream(bais)) {
                return (Auto) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public Memento createMemento() {
        Memento memento = new Memento();
        memento.setAuto(this);
        return memento;
    }

    public void setMemento(Memento memento) {
        Auto restoredAuto = memento.getAuto();
        if (restoredAuto != null) {
            this.brand = restoredAuto.getBrand();
            this.models = restoredAuto.models;
        }
    }

    public Iterator<Model> iterator() {
        return new AutoIterator();
    }

    public int getSize() {
        return models.length;
    }


    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (isUnique(newName)) {
            getModel(oldName).modelName = newName;
        } else throw new DuplicateModelNameException(oldName);
    }

    @Override
    public String[] getModelNames() {
        String[] modelNames = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            modelNames[i] = models[i].modelName;
        }
        return modelNames;
    }

    @Override
    public double getPriceByName(String modelName) throws NoSuchModelNameException {
        return getModel(modelName).price;
    }

    @Override
    public void setPriceByName(String modelName, double price) throws NoSuchModelNameException {
        if (price > 0.0) {
            getModel(modelName).price = price;
        } else throw new ModelPriceOutOfBoundsException("Price must be greater than 0");
    }

    @Override
    public double[] getModelPrices() {
        double[] modelPrices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            modelPrices[i] = models[i].price;
        }
        return modelPrices;
    }

    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        Model[] newModels = Arrays.copyOf(models, models.length + 1);
        if (isUnique(modelName)) {
            if (price > 0.0)
                newModels[newModels.length - 1] = new Model(modelName, price);
            else throw new ModelPriceOutOfBoundsException("Price must be greater than 0");
            models = newModels;
        } else throw new DuplicateModelNameException(modelName);
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        int index = -1;
        boolean flag = false;
        for (int i = 0; i < models.length; i++) {
            if (models[i].modelName.equals(modelName)) {
                index = i;
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new NoSuchModelNameException(modelName);
        }

        Model[] newModels = Arrays.copyOf(models, models.length - 1);
        if (index == 0) {
            System.arraycopy(models, 1, newModels, 0, models.length - 1);
        } else {
            System.arraycopy(models, 0, newModels, 0, index);
            System.arraycopy(models, index + 1, newModels, index, models.length - index - 1);
        }
        models = newModels;
    }

    private Model getModel(String modelName) throws NoSuchModelNameException {
        for (Model model : models) {
            if (model.modelName.equals(modelName)) {
                return model;
            }
        }
        throw new NoSuchModelNameException(modelName);
    }

    private boolean isUnique(String modelName) {
        boolean flag = false;
        for (Model model : models) {
            if (model.modelName.equals(modelName)) {
                flag = false;
                break;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    //Command
    private Command cmd;

    public void print(PrintWriter pw) throws Exception {
        cmd.writeInFile(pw, this);
    }

    public void setPrintCommand(Command cmd) {
        this.cmd = cmd;
    }

    public void printAuto(Auto auto) {
        System.out.println("Brand: " + auto.getBrand());
        for (int i = 0; i < auto.getSize(); i++) {
            System.out.println("Model: " + auto.getModelNames()[i] + " Price: " + auto.getModelPrices()[i]);
        }
    }

    //Visitor
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public static void main(String[] args) throws NoSuchModelNameException {
        // Iterator
        Auto auto = new Auto("Toyota", 3);
        try {
            auto.addModel("Camry", 25000);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }

        System.out.println("Бренд: " + auto.getBrand());
        System.out.println("Модели через итератор:");
        Iterator<Auto.Model> it = auto.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        //Memento
        System.out.println();
        Auto bmw = new Auto("BMW", 3);
        System.out.println("До изменений:");
        bmw.printAuto(bmw);

        Auto.Memento memento = bmw.createMemento();

        try {
            bmw.setModelName("BMW1", "BMW X5");
            bmw.setPriceByName("BMW X5", 25000);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }

        System.out.println("После изменений:");
        bmw.printAuto(bmw);


        System.out.println("Восстанавливаем состояние:");
        bmw.setMemento(memento);
        bmw.printAuto(bmw);
    }
}
