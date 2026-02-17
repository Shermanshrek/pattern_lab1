package vehicle;

import exceptions.DuplicateModelNameException;
import exceptions.ModelPriceOutOfBoundsException;
import exceptions.NoSuchModelNameException;

import java.util.Arrays;

public class Auto implements Vehicle, Cloneable {
    private String brand;
    private Model[] models;

    public Auto(String brand, int size) {
        this.brand = brand;
        models = new Model[size];
        for (int i = 0; i < size; i++) {
            models[i] = new Model("Auto" + i, 1 + i);
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

    private class Model implements Cloneable {
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
}
