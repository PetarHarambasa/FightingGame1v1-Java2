package hr.algebra.java2.utils;

import hr.algebra.java2.model.*;

public class ClassPLayerUtil {

    private static Archer archer = Archer.getInstance();
    private static Warrior warrior = Warrior.getInstance();
    private static Wizard wizard = Wizard.getInstance();
    private static HorseMan horseMan = HorseMan.getInstance();
    private static Assassin assassin = Assassin.getInstance();

    public static int selectedHp(Characters playerClass) {
        if (playerClass == Characters.Archer) {
            return archer.getClassHealthPoints();
        } else if (playerClass == Characters.Warrior) {
            return warrior.getClassHealthPoints();
        } else if (playerClass == Characters.Wizard) {
            return wizard.getClassHealthPoints();
        } else if (playerClass == Characters.HorseMan) {
            return horseMan.getClassHealthPoints();
        } else
            return assassin.getClassHealthPoints();
    }
}
