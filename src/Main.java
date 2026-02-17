import adapter.StringStreamAdapter;
import config.ConfigLoader;
import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;
import vehicle.Motocycle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {
        try{
            String[] originStrings = {"привет", "это", "я"};
            System.out.println("Строка до адаптации: " + Arrays.toString(originStrings));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            StringStreamAdapter.writeStrings(baos, originStrings);
            byte[] bytes = baos.toByteArray();
            System.out.println("Записано байт: " + bytes.length);
            System.out.println("Байты: " + Arrays.toString(bytes));

            // чтение строк из потока
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            String[] restoredStrings = StringStreamAdapter.readStrings(bais);
            System.out.println("Восстановленные строки: " + Arrays.toString(restoredStrings));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}