package pl.krkteam.battleships.opponent.shot.response.dto;

public class OpponentShotHit extends OpponentShotResult {

    public OpponentShotHit(int rowIndex, int columnIndex) {
        super("ShotHit", rowIndex, columnIndex);
    }

}
