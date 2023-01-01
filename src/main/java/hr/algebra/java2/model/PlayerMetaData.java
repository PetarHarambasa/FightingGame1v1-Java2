package hr.algebra.java2.model;

import java.io.Serializable;

public class PlayerMetaData implements Serializable {
    private static final long serialVersionUID = 4L;

    private String ipAddress;
    private String port;
    private String playerName;
    private String idPlayer;

    public PlayerMetaData() {
    }

    public PlayerMetaData(String ipAddress, String port, String playerName, String idPlayer) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.playerName = playerName;
        this.idPlayer = idPlayer;
    }

    public PlayerMetaData(String playerName, String idPlayer) {
        this.playerName = playerName;
        this.idPlayer = idPlayer;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }
}
