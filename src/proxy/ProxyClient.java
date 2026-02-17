package proxy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

record ProxyClient(Socket socket) implements Runnable {
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
