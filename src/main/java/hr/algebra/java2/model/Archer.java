package hr.algebra.java2.model;

import java.util.List;

public class Archer extends CharacterClass {

    private static Archer singleInstance = null;
    private static final int HP = 3400;
    private static final List<String> MOVE_ONE_LIST = List.of(new String[]{"Arrow attack", "Shockwave arrow attack", "Stun arrow attack", "Curse arrow attack", "Paralyze arrow attack"});
    private static final List<String> MOVE_TWO_LIST = List.of(new String[]{"Multi-arrow attack", "Multi-shockwave arrow attack", "Multi-curse arrow attack", "Multi-stun arrow attack", "Multi-paralyze arrow attack"});
    private static final List<String> MOVE_THREE_LIST = List.of(new String[]{"Special arrow attack", "Ultimate arrow attack", "Limitless arrow attack", "Void arrow attack", "Arrow of destruction attack"});
    private static final String IMAGE_PATH = "src/main/resources/images/rsz_gondor_archer_icon.jpg";
    private Archer() {
        super(HP, MOVE_ONE_LIST, MOVE_TWO_LIST, MOVE_THREE_LIST, IMAGE_PATH);
    }

    public static Archer getInstance() {
        if (singleInstance == null)
            singleInstance = new Archer();

        return singleInstance;
    }
}
