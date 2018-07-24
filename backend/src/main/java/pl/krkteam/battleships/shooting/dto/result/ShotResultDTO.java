package pl.krkteam.battleships.shooting.dto.result;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

public abstract class ShotResultDTO {

    String resultMessage;

   public abstract OpponentShotResult getOpponentShotResult(int x, int y);

}
