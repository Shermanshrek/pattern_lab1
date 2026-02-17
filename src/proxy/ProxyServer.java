package proxy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxyServer {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Подключился клиент: " + socket.getInetAddress());
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private record ClientHandler(Socket socket) implements Runnable {

        @Override
            public void run() {
                try (DataInputStream in = new DataInputStream(socket.getInputStream());
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                    double a = in.readDouble();
                    double b = in.readDouble();

                    System.out.printf("Получены числа %f и %f%n", a, b);

                    double res = a * b;

                    out.writeDouble(res);
                    out.flush();
                    System.out.println("Отправлен результат: " + res);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        socket.close();
                    } catch (IOException _) {

                    }
                }
            }
        }
}
