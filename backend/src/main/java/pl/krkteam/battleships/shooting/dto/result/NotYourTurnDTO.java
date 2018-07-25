package pl.krkteam.battleships.shooting.dto.result;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentNoShot;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

public class NotYourTurnDTO extends ShotResultDTO {

    public NotYourTurnDTO() {
        resultMessage = "ItIsNotYourTurn";
    }

    @Override
    public OpponentShotResult getOpponentShotResult(int y, int x) {
        return new OpponentNoShot();
    }
}

