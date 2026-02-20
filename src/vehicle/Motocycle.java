package vehicle;

import exceptions.DuplicateModelNameException;
import exceptions.ModelPriceOutOfBoundsException;
import exceptions.NoSuchModelNameException;

public class Motocycle implements Vehicle, Cloneable {
    private String brand;
    private Model head = new Model();

    {
        head.next = head;
        head.prev = head;
    }

    private int size = 0;

    public Motocycle(String brand, int size) throws DuplicateModelNameException {
        this.brand = brand;
        this.size = 0;
        for (int i = 0; i < size; i++) {
            addModel(brand + i, 1 + i);
        }
    }

    @Override
    public Motocycle clone() throws CloneNotSupportedException {
        Motocycle cloned = (Motocycle) super.clone();

        cloned.head = new Model();
        cloned.head.prev = cloned.head;
        cloned.head.next = cloned.head;
//        cloned.size = 0;

        Model current = this.head.next;
        while (current != this.head) {
            Model newModel = new Model();
            newModel.modelName = current.modelName;
            newModel.price = current.price;

            Model last = cloned.head.prev;
            last.next = newModel;
            newModel.prev = last;
            newModel.next = cloned.head;
            cloned.head.prev = newModel;

//            cloned.size++;
            current = current.next;
        }

        return cloned;
    }

    private class Model implements Cloneable {
        String modelName = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;

        @Override
        protected Model clone() throws CloneNotSupportedException {
            Model mdl = null;
            try {
                mdl = (Model) super.clone();
            } catch (CloneNotSupportedException ex) {
                System.out.println("Cloning goes wrong...");
            }
            return mdl;
        }
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
        } else throw new DuplicateModelNameException(newName);
    }

    @Override
    public String[] getModelNames() {
        String[] modelNames = new String[size];
        Model p = head.next;
        int i = 0;
        while (p != head && i < size) {
            modelNames[i] = p.modelName;
            p = p.next;
            i++;
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
        } else throw new ModelPriceOutOfBoundsException("Price " + modelName + " out of bounds");
    }

    @Override
    public double[] getModelPrices() {
        double[] modelPrices = new double[size];
        Model p = head.next;
        int i = 0;
        while (p != head && i < size) {
            modelPrices[i] = p.price;
            p = p.next;
            i++;
        }
        return modelPrices;
    }

    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        if (isUnique(modelName)) {
            if (price > 0.0) {
                Model newModel = new Model();
                newModel.next = head;
                newModel.prev = head.prev;
                head.prev.next = newModel;
                head.prev = newModel;
                newModel.modelName = modelName;
                newModel.price = price;
                size++;
            } else throw new ModelPriceOutOfBoundsException("Price " + modelName + " out of bounds");
        } else throw new DuplicateModelNameException(modelName);
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        Model beingDeleted = getModel(modelName);
        beingDeleted.prev.next = beingDeleted.next;
        beingDeleted.next.prev = beingDeleted.prev;
        size--;
    }

    private Model getModel(String modelName) throws NoSuchModelNameException {
        Model p = head.next;
        while (p != head) {
            if (p.modelName.equals(modelName)) {
                return p;
            }
            p = p.next;
        }
        throw new NoSuchModelNameException(modelName);
    }

    private boolean isUnique(String modelName) {
        Model p = head.next;
        while (p != head) {
            if (p.modelName.equals(modelName)) {
                return false;
            }
            p = p.next;
        }
        return true;
    }
}
