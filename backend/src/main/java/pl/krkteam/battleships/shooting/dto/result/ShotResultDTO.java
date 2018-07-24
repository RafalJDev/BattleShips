package pl.krkteam.battleships.shooting.dto.result;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

public abstract class ShotResultDTO {
    
    String resultMessage;

    public String getMessage() {
        return resultMessage;
    }
    
    public abstract OpponentShotResult getOpponentShotResult(int y, int x);

}
