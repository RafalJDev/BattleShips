package pl.krkteam.battleships.common.domain;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.opponent.shot.response.domain.ShotResultQueueHolder;
import pl.krkteam.battleships.turns.holding.TurnHolder;

@Component
public class Game {

    private GameBoardHolder gameBoardHolder = new GameBoardHolder();

    private ShotResultQueueHolder shotResultQueueHolder = new ShotResultQueueHolder();

    public TurnHolder getTurnHolder() {
        return turnHolder;
    }

    private TurnHolder turnHolder = new TurnHolder();

    public GameBoardHolder getGameBoardHolder() {
        return gameBoardHolder;
    }

    public ShotResultQueueHolder getShotResultQueueHolder() {
        return shotResultQueueHolder;
    }
}
