package example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Test2 {
    public static void main(String[] args) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("test.zip"))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            if (!zipEntry.isDirectory()) {
                File file = new File("file4.txt");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = zipInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }
                fileOutputStream.close();
            }

        } catch (IOException e) {
            System.err.println("error" + e);
        }
    }
}
