package pl.krkteam.battleships.shooting.dto.result;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotHit;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

public class ResultHitDTO extends ShotResultDTO {

    public ResultHitDTO() {
        resultMessage = "ResultHit";
    }

    @Override
    public OpponentShotResult getOpponentShotResult(int y, int x) {
        return new OpponentShotHit(y, x);
    }
}
