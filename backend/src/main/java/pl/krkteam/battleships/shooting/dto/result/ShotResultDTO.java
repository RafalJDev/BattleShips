package pl.krkteam.battleships.shooting.dto.result;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

public abstract class ShotResultDTO {

    public String getMessage() {
        return resultMessage;
    }

    String resultMessage;

   public abstract OpponentShotResult getOpponentShotResult(int x, int y);

}
