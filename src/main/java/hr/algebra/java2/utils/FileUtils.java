package hr.algebra.java2.utils;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUtils {
    public static Image getImage(String path) throws FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(path);

        return new Image(fileStream);
    }
}
