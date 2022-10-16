package hr.algebra.java2.model;

import javafx.scene.image.ImageView;

import java.util.List;

public class CharacterClass {
    private final int classHealthPoints;
    private final List<String> classMovesOne;
    private final List<String> classMovesTwo;
    private final List<String> classMovesThree;
    private final String IMAGE_PATH;

    public CharacterClass(int classHealthPoints, List<String> classMovesOne, List<String> classMovesTwo, List<String> classMovesThree, String IMAGE_PATH) {
        this.classHealthPoints = classHealthPoints;
        this.classMovesOne = classMovesOne;
        this.classMovesTwo = classMovesTwo;
        this.classMovesThree = classMovesThree;
        this.IMAGE_PATH = IMAGE_PATH;
    }

    public int getClassHealthPoints() {
        return classHealthPoints;
    }

    public List<String> getClassMovesOne() {
        return classMovesOne;
    }

    public List<String> getClassMovesTwo() {
        return classMovesTwo;
    }

    public List<String> getClassMovesThree() {
        return classMovesThree;
    }

    public String getIMAGE_PATH() {
        return IMAGE_PATH;
    }
}
