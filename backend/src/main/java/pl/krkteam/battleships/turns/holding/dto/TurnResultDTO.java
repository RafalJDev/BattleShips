package pl.krkteam.battleships.turns.holding.dto;

public class TurnResultDTO {

    private String message;

    public TurnResultDTO(Boolean result) {
        this.message = result.toString();
    }
}
