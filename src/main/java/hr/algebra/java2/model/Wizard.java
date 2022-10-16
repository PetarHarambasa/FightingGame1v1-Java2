package hr.algebra.java2.model;

import java.util.List;

public class Wizard extends CharacterClass {
    private static Wizard singleInstance = null;
    private static final int HP = 3800;
    private static final List<String> MOVE_ONE_LIST = List.of(new String[]{"Fireball attack", "Blinding attack", "Lighting attack", "Gravity attack", "Shockwave attack"});
    private static final List<String> MOVE_TWO_LIST = List.of(new String[]{"Meteor attack", "Sunlight attack", "Storm attack", "Flesh consuming attack", "Explosion attack"});
    private static final List<String> MOVE_THREE_LIST = List.of(new String[]{"Ultimate sorcerer's attack", "Knowledge of the universe attack", "Star falling attack", "Unknown magic attack of Gods", "The almighty attack of secret creatures"});
    private static final String IMAGE_PATH = "src/main/resources/images/Gandalf.jpg";
    public Wizard() {
        super(HP, MOVE_ONE_LIST, MOVE_TWO_LIST, MOVE_THREE_LIST, IMAGE_PATH);
    }

    public static Wizard getInstance()
    {
        if (singleInstance == null)
            singleInstance = new Wizard();

        return singleInstance;
    }
}
