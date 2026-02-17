import adapter.StringStreamAdapter;
import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException, IOException {
        String[] originalStrings = {"привет", "это", "я"};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println(Arrays.toString(originalStrings));

        StringStreamAdapter.writeStrings(baos, originalStrings);
        byte[] byteData = baos.toByteArray();
        System.out.println("Записано байт: " + byteData.length);
        System.out.println("Содержимое байтов (десятичные значения): " + Arrays.toString(byteData));

        ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
        String[] restoredStrings = StringStreamAdapter.readStrings(bais);
        System.out.println("Восстановленные строки: " + Arrays.toString(restoredStrings));
    }
}