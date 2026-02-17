package proxy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Proxy {
    private final int serverPort;
    private final String serverHost;

    public Proxy(int serverPort, String serverHost) {
        this.serverPort = serverPort;
        this.serverHost = serverHost;
    }

    public double multiply(double a, double b) {
        try (Socket socket = new Socket(serverHost, serverPort);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream()))
        {
            out.writeDouble(a);
            out.writeDouble(b);
            out.flush();

            return in.readDouble();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Proxy proxy = new Proxy(5000, "localhost");

        double[][] tests = {
                {2.5, 3.0},
                {-1.2, 5.5},
                {0.0, 100.5},
                {7.8, 0.0}
        };

        for (double[] test : tests) {
            try {
                double result = proxy.multiply(test[0], test[1]);
                System.out.printf("%f * %f = %f%n", test[0], test[1], result);
            } catch (Exception e) {
                System.err.printf("Ошибка для пары (%f, %f): %s%n", test[0], test[1], e.getMessage());
            }
        }
    }
}
