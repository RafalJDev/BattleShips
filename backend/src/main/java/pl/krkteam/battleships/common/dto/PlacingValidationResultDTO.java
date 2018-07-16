package pl.krkteam.battleships.common.dto;

public class PlacingValidationResultDTO {
    private Result result;

    public PlacingValidationResultDTO(Result result) {
        this.result = result;
    }

    public enum Result {
        OK, WRONG
    }

    public Result getResult() {
        return result;
    }
}
