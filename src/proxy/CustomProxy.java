package proxy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class CustomProxy {
    private final int serverPort;
    private final String serverHost;

    public CustomProxy(int serverPort, String serverHost) {
        this.serverPort = serverPort;
        this.serverHost = serverHost;
    }

    public double multiply(double a, double b) {
        try (Socket socket = new Socket(serverHost, serverPort);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            out.writeDouble(a);
            out.writeDouble(b);
            out.flush();

            return in.readDouble();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
