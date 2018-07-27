package pl.krkteam.battleships.common.dto;

public class PlayerDTO {

    private String name;

    public PlayerDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player: {name: " + name + "}";
    }
}
