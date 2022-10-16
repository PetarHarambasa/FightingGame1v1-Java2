package hr.algebra.java2.model;

import java.util.List;

public class Warrior extends CharacterClass{
    private static Warrior singleInstance = null;
    private static final int HP = 4000;
    private static final List<String> MOVE_ONE_LIST = List.of(new String[]{"Sword attack", "Spin sword attack", "Armor piercing sword attack", "Counter attack", "Leap sword attack"});
    private static final List<String> MOVE_TWO_LIST = List.of(new String[]{"Heavy sword attack", "Slash of Elendil attack", "Beheading attack", "3 part combo attack", "Darkness cleaver attack"});
    private static final List<String> MOVE_THREE_LIST = List.of(new String[]{"Final demolishing sword attack", "Enhanced sword attack", "Forbidden sword technique attack", "Unforgettable sword attack", "Unknown sword attack of ancients"});
    private static final String IMAGE_PATH = "src/main/resources/images/Aragorn.jpg";
    private Warrior() {
        super(HP, MOVE_ONE_LIST, MOVE_TWO_LIST, MOVE_THREE_LIST, IMAGE_PATH);
    }

    public static Warrior getInstance()
    {
        if (singleInstance == null)
            singleInstance = new Warrior();

        return singleInstance;
    }
}
