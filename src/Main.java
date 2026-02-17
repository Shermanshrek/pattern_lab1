import config.ConfigLoader;
import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;
import vehicle.Motocycle;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {
//        ConfigLoader configLoader1 = ConfigLoader.getInstance();
//        ConfigLoader configLoader2 = ConfigLoader.getInstance();
//        System.out.println(configLoader1.getProperties());
//        if (configLoader1 == configLoader2) {
//            System.out.println("true");
//        }
//        else System.out.println("false");
        Motocycle moto1 = new Motocycle("biba", 5);
        Motocycle moto2 = moto1.clone();

        moto1.setModelName("Moto1", "test");
        System.out.println(Arrays.toString(moto1.getModelNames()));
        System.out.println(Arrays.toString(moto2.getModelNames()));
    }
}