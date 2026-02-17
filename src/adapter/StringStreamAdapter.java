package adapter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StringStreamAdapter {

    public static void writeStrings(OutputStream out, String[] strings) throws IOException {
        for (String s : strings) {
            out.write(s.getBytes(StandardCharsets.UTF_8));
            out.write('\n');
        }
        out.flush();
    }

    public static String[] readStrings(InputStream in) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }
        return list.toArray(new String[0]);
    }
}