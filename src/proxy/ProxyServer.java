package proxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ProxyServer {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Подключился клиент: " + socket.getInetAddress());
                new Thread(new ServerMultiplier(socket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
