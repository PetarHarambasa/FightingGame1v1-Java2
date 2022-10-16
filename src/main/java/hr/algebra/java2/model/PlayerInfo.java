package hr.algebra.java2.model;

import javafx.scene.image.Image;

public class PlayerInfo {
    String playerName;
    Characters characterClass;
    int healthPoints;
    Image playerImage;
    int numberOfWins;
    long bestVictoryTime;

    public PlayerInfo(String playerName, Characters characterClass, int healthPoints, Image playerImageView) {
        this.playerName = playerName;
        this.characterClass = characterClass;
        this.healthPoints = healthPoints;
        this.playerImage = playerImageView;
        numberOfWins = 0;
        bestVictoryTime = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public long getBestVictoryTime() {
        return bestVictoryTime;
    }

    public void setBestVictoryTime(long bestVictoryTime) {
        this.bestVictoryTime = bestVictoryTime;
    }

    public Characters getCharacterClass() {
        return characterClass;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCharacterClass(Characters characterClass) {
        this.characterClass = characterClass;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setPlayerImage(Image playerImageView) {
        this.playerImage = playerImageView;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }
}
