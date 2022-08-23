package rikkei.academy.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Config<T> {
    public static Scanner sc;

    public static Scanner scanner() {
        if (sc == null) {
            sc = new Scanner(System.in);
            return sc;
        }
        return sc;
    }

    public T read(String path) {
        T data = null;
        try (
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            data = (T) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error reading");
        }
        return data;
    }

    public void write(String path, T data) {
        try (
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(data);
        } catch (Exception e) {
            System.out.println("Error writing");
        }
    }

}