package Visitor;

import vehicle.Auto;
import vehicle.Motorcycle;
import vehicle.Vehicle;

public interface Visitor {
    void visit(Auto vehicle);
    void visit(Motorcycle vehicle);
}
