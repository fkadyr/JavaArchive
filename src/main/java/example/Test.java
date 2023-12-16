package example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Test {
    public static void main(String[] args) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("test.zip"))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                String zipFileName = zipEntry.getName();
                System.out.println(zipFileName);
                zipEntry = zipInputStream.getNextEntry();
            }

        } catch (IOException e) {
            System.err.println("error" + e);
        }
    }
}
