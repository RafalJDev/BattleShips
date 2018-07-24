package pl.krkteam.battleships.shooting.dto.result;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotPlayerLoose;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

public class ResultPlayerWonDTO extends ShotResultDTO {
    public ResultPlayerWonDTO() {
        this.resultMessage = "PlayerWon";
    }

    @Override
    public OpponentShotResult getOpponentShotResult(int x, int y) {
        return new OpponentShotPlayerLoose(y, x);
    }
}
