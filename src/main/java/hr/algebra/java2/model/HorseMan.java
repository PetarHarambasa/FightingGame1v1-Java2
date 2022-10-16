package hr.algebra.java2.model;

import java.util.List;

public class HorseMan extends CharacterClass{
    private static HorseMan singleInstance = null;
    private static final int HP = 4500;
    private static final List<String> MOVE_ONE_LIST  = List.of(new String[]{"Horse charge attack", "Spear attack", "Rohirrim horn call", "Horse knockout attack", "Horse back to back attack"});
    private static final List<String> MOVE_TWO_LIST  = List.of(new String[]{"Horse stomp attack", "Spear throwing attack", "Spear of light attack", "Orc spear cleaver", "Brave horse charge attack"});
    private static final List<String> MOVE_THREE_LIST = List.of(new String[]{"Unstoppable charge attack", "Charging horse stomp attack", "Horseman's anger attack", "Revenging Horseman attack", "Unrecoverable horse charge attack"});
    private static final String IMAGE_PATH = "src/main/resources/images/horseMan.jpg";
    private HorseMan() {
        super(HP, MOVE_ONE_LIST, MOVE_TWO_LIST, MOVE_THREE_LIST, IMAGE_PATH);
    }

    public static HorseMan getInstance()
    {
        if (singleInstance == null)
            singleInstance = new HorseMan();

        return singleInstance;
    }
}
