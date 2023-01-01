package hr.algebra.java2.model;

import java.io.Serializable;

public class GameStateDto implements Serializable {
    private String name;

    private SerializablePlayer serializablePlayer;

    public SerializablePlayer getSerializablePlayer() {
        return serializablePlayer;
    }

    public void setSerializablePlayer(SerializablePlayer serializablePlayer) {
        this.serializablePlayer = serializablePlayer;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}