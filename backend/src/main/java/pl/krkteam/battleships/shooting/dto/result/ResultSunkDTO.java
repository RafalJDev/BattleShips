package pl.krkteam.battleships.shooting.dto.result;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotSunk;

public class ResultSunkDTO extends ShotResultDTO {

    public ResultSunkDTO() {
        this.resultMessage = "ResultSunk";
    }

    @Override
    public OpponentShotResult getOpponentShotResult(int x, int y) {
        return new OpponentShotSunk(y, x);
    }
}
