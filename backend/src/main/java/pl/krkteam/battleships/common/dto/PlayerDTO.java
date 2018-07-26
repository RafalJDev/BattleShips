package pl.krkteam.battleships.common.dto;

public class PlayerDTO {

    public String name;

    public PlayerDTO() {
    }

    public PlayerDTO(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player: {name: " + name + "}";
    }
}
