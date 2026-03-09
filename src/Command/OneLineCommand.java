package Command;

import ChainOfResponsibility.PrintInLine;
import utils.VehicleUtils;
import vehicle.Auto;

import java.io.PrintWriter;

public class OneLineCommand implements Command {

    @Override
    public void writeInFile(PrintWriter pw, Auto auto) {
        VehicleUtils.writeInline(auto, pw);
    }
}
