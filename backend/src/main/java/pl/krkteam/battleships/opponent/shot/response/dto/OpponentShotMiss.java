package pl.krkteam.battleships.opponent.shot.response.dto;

public class OpponentShotMiss extends OpponentShotResult {

    public OpponentShotMiss(int rowIndex, int columnIndex) {
        super("ShotMiss", rowIndex, columnIndex);
    }
}
