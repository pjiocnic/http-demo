package com.pluralsight.httpclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileProcessorPreJDK11 {

    public static void main(String[] args) {
        writeFile();
        readFile();
    }

    private static void writeFile() {
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("hellworld.txt"))) {
            String fileContent = "Hello World!";
            bufferedOutputStream.write(fileContent.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file", e);
        }
    }

    private static void readFile() {
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("hellworld.txt"))) {
            int ch = bufferedInputStream.read();
            while(ch != -1) {
                System.out.print((char)ch);
                ch = bufferedInputStream.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

}
