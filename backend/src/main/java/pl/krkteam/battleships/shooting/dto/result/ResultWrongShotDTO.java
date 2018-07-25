package pl.krkteam.battleships.shooting.dto.result;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentNoShot;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

public class ResultWrongShotDTO extends ShotResultDTO {

    public ResultWrongShotDTO() {
        this.resultMessage = "WrongShot";
    }

    @Override
    public OpponentShotResult getOpponentShotResult(int y, int x) {
        return new OpponentNoShot();
    }
}
