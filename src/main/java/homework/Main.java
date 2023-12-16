package homework;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String[] files = {"add1.txt", "add2.txt"}; // Массив файлов для довбаления
        String zipName = "test.zip"; //Имя архива

        addFileZip(zipName, files); // Функция добавления новых файлов в существующий архив
    }

    public static void addFileZip(String zipName, String[] files) {
        String tempZipFile = "tempzip.zip";     //Имя промежуточного файла
        File exZipFile = new File(zipName);     //Создаем объект File, zip файл переданный по параметру
        try {                                   //Открываем исключение, для ловли ошибок
            FileInputStream fileInputStream = new FileInputStream(exZipFile); // Создаем поток файла для чтения
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);// Открываем поток-архив, для считывания
            FileOutputStream fileOutputStream = new FileOutputStream(tempZipFile); // Создаем поток файла для записи
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);//Открываем поток-архив, для записи

            ZipEntry zipEntry; //Создаем zipEntry для чтения по одному объекту архива
            while ((zipEntry = zipInputStream.getNextEntry()) != null) { // пока не конец архива читаем
                zipOutputStream.putNextEntry(zipEntry); //добавляем по одному объекту из старого архива, в новый
                                                        //после этой функции будет создана запись в архиве
                byte[] buffer = new byte[1024];         //создаем буффер размером 1024 для чтения, и записи
                int length;                             //переменная для хранения количесво байт
                while ((length = zipInputStream.read(buffer)) != -1) { //Читает байты из zipInputStream в буфер buffer
                    zipOutputStream.write(buffer, 0, length);       //и записывает их в fileOutputStream.
                }
            }

            fileInputStream.close(); // Закрываем поток записи в файл


            for (String fileToAdd : files) { // Далее проходим по массиву файлов который нужно записать в архив
                File file = new File(fileToAdd); // Создаем объект File

                FileInputStream fis = new FileInputStream(file); // Передаем в поток для чтения

                ZipEntry zipEntryAdd = new ZipEntry(fileToAdd); // Добавляем файл в zipEntry

                zipOutputStream.putNextEntry(zipEntryAdd); // а zipEntry добавляем в поток-архив для записи

                byte[] buffer = new byte[1024];     //создаем буффер размером 1024 для чтения, и записи
                int length;                         //переменная для хранения количесво байт
                while ((length = fis.read(buffer)) > -1) { // Считваем байты из потока для чтения
                    zipOutputStream.write(buffer, 0, length); // Пока не конец записываем
                }

                fis.close(); //Закрываем поток для чтения
            }

            zipInputStream.closeEntry(); //Закрываем поток-архив для чтения
            zipInputStream.close();

            zipOutputStream.closeEntry(); //Закрываем поток для записи
            zipOutputStream.close();

            exZipFile.delete(); // удаляем старый файл
            File temp = new File(tempZipFile); // создаем файл нового архива
            temp.renameTo(exZipFile); //даем имя старого архива, новому
            System.out.println("successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
