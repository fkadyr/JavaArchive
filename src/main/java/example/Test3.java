package example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Test3 {
    public static void main(String[] args) {

        String file = "file.txt";

        try (ZipOutputStream zipOutputStream = new ZipOutputStream( new FileOutputStream("test2.zip"));
                FileInputStream fileInputStream = new FileInputStream(file)) {

            ZipEntry zipEntry = new ZipEntry(file);

            zipOutputStream.putNextEntry(zipEntry);

            int length;
            byte[] buffer = new byte[1024];
            while ((length = fileInputStream.read(buffer))>0) {
                zipOutputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.err.println("error "+e);
        }
    }
}
