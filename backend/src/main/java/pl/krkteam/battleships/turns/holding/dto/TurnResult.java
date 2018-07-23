package pl.krkteam.battleships.turns.holding.dto;

public class TurnResult {

    private String  message;

    public TurnResult(Boolean result) {
        this.message = result.toString();
    }
}
