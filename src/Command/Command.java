package Command;

import vehicle.Auto;

import java.io.PrintWriter;

public interface Command {
    void writeInFile(PrintWriter pw, Auto auto) throws Exception;
}
