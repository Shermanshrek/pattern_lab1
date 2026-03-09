package Command;

import utils.VehicleUtils;
import vehicle.Auto;

import java.io.PrintWriter;

public class ColumnCommand implements Command {
    @Override
    public void writeInFile(PrintWriter pw, Auto auto) throws Exception {
        VehicleUtils.writeColumn(auto, pw);
    }
}

