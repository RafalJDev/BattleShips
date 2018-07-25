package pl.krkteam.battleships.opponent.shot.response.dto;

import pl.krkteam.battleships.common.dto.CoordinateDTO;
import pl.krkteam.battleships.shooting.dto.ShotDTO;

public abstract class OpponentShotResult {

    private String opponentShotResult;
    private ShotDTO opponentShotDTO;

    public String getMessage() {
        return opponentShotResult;
    }

    public OpponentShotResult(String opponentShotResult, int rowIndex, int columnIndex) {
        createOpponentShotResult(opponentShotResult, rowIndex, columnIndex);
    }

    private void createOpponentShotResult(String opponentShotResult, int rowIndex, int columnIndex) {
        this.opponentShotResult = opponentShotResult;
        final CoordinateDTO shotCoordinate = new CoordinateDTO(rowIndex, columnIndex);
        this.opponentShotDTO = new ShotDTO(shotCoordinate);
    }
}
