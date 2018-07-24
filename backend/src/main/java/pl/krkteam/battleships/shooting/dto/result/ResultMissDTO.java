package pl.krkteam.battleships.shooting.dto.result;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotMiss;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

public class ResultMissDTO extends ShotResultDTO {

    public ResultMissDTO() {
        resultMessage = "ResultMiss";
    }

    @Override
    public OpponentShotResult getOpponentShotResult(int x, int y) {
        return new OpponentShotMiss(y, x);
    }
}
