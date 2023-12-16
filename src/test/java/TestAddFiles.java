import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TestAddFiles {

    @Test
    public void testAddFileToArchive(){
        String[] files = {"add1.txt", "add2.txt"};
        String zipName = "test.zip";

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("test.zip"))) {
            for (String file: files) {
                ZipEntry zipEntry = zipInputStream.getNextEntry();
                if (!zipEntry.isDirectory()) {
                    File fil = new File(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(fil);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, length);
                    }
                    fileOutputStream.close();
                    Assertions.assertTrue(fil.exists());
                    fil.delete();
                }

            }

        } catch (IOException e) {
            System.err.println("error" + e);
        }
    }
}
