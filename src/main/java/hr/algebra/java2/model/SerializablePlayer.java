package hr.algebra.java2.model;


import java.io.Serializable;
import java.util.List;

public class SerializablePlayer implements Serializable {

    private static final long serialVersionUID = 4L;
    private String playerName;
    private Characters characterClass;
    private int healthPoints;
    private int numberOfWins;
    private long bestVictoryTime;
    private List<List<String>> movesList;
    public SerializablePlayer(String playerName, Characters characterClass, int healthPoints, int numberOfWins, long bestVictoryTime, List<List<String>> movesList) {
        this.playerName = playerName;
        this.characterClass = characterClass;
        this.healthPoints = healthPoints;
        this.numberOfWins = numberOfWins;
        this.bestVictoryTime = bestVictoryTime;
        this.movesList = movesList;
    }

    public SerializablePlayer(String playerName, Characters characterClass, int healthPoints) {
        this.playerName = playerName;
        this.characterClass = characterClass;
        this.healthPoints = healthPoints;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Characters getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(Characters characterClass) {
        this.characterClass = characterClass;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public long getBestVictoryTime() {
        return bestVictoryTime;
    }

    public void setBestVictoryTime(long bestVictoryTime) {
        this.bestVictoryTime = bestVictoryTime;
    }

    public List<List<String>> getMovesList() {
        return movesList;
    }

    public void setMovesList(List<List<String>> movesList) {
        this.movesList = movesList;
    }

}
