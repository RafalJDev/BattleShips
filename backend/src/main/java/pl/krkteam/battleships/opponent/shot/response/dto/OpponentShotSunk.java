package pl.krkteam.battleships.opponent.shot.response.dto;

public class OpponentShotSunk extends OpponentShotResult {

    public OpponentShotSunk(int rowIndex, int columnIndex) {
        super("ShotSunk", rowIndex, columnIndex);
    }
}
