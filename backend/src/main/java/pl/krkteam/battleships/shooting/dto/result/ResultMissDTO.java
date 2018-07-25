package pl.krkteam.battleships.shooting.dto.result;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotMiss;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

public class ResultMissDTO extends ShotResultDTO {

    public ResultMissDTO() {
        resultMessage = "ResultMiss";
    }

    @Override
    public OpponentShotResult getOpponentShotResult(int y, int x) {
        return new OpponentShotMiss(y, x);
    }
}
