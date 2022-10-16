package hr.algebra.java2.model;

import java.util.List;

public class Assassin extends CharacterClass {
    private static Assassin singleInstance = null;
    private static final int HP = 3000;
    private static final List<String> MOVE_ONE_LIST = List.of(new String[]{"Stagger attack", "Backstab attack", "Dagger attack", "Invisible attack", "Knife throw"});
    private static final List<String> MOVE_TWO_LIST = List.of(new String[]{"Multi-knife throw attack", "Throat slicer attack", "Knife in eye attack", "Dagger slicer attack", "Bleeding attack"});
    private static final List<String> MOVE_THREE_LIST = List.of(new String[]{"Deadly unseen attack", "Assassin's deadliest attack", "Assassin's secret attack", "Ultimate dagger attack", "Forever bleeding attack"});
    private static final String IMAGE_PATH = "src/main/resources/images/assasin.jpg";
    private Assassin() {
        super(HP, MOVE_ONE_LIST, MOVE_TWO_LIST, MOVE_THREE_LIST,IMAGE_PATH);
    }

    public static Assassin getInstance()
    {
        if (singleInstance == null)
            singleInstance = new Assassin();

        return singleInstance;
    }
}
