package pl.krkteam.battleships.common.domain;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.opponent.shot.response.domain.ShotResultQueue;
import pl.krkteam.battleships.opponent.shot.response.domain.ShotResultQueueHolder;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;
import pl.krkteam.battleships.turns.holding.TurnHolder;

@Component
public class Game {

    public void initializeGame(Player playerA, Player playerB){
        addPlayer(playerA);
        addPlayer(playerB);
    }

    private GameBoardHolder gameBoardHolder = new GameBoardHolder();

    private ShotResultQueueHolder shotResultQueueHolder = new ShotResultQueueHolder();

    private TurnHolder turnHolder = new TurnHolder();

    public GameBoardHolder getGameBoardHolder() {
        return gameBoardHolder;
    }

    public ShotResultQueueHolder getShotResultQueueHolder() {
        return shotResultQueueHolder;
    }

    public TurnHolder getTurnHolder() {
        return turnHolder;
    }

    public void addShotResultToQueue(Player opponentPlayer, OpponentShotResult opponentShotResult) {
        final ShotResultQueue shotResultQueue = shotResultQueueHolder.getShotResultQueue(opponentPlayer);
        shotResultQueue.addShotResult(opponentShotResult);
    }

    public OpponentShotResult getShotResultFromQueue(Player opponentPlayer) {
        final ShotResultQueue shotResultQueue = shotResultQueueHolder.getShotResultQueue(opponentPlayer);
        return shotResultQueue.getOpponentShotResult();
    }

    public void addPlayer(Player player) {
        gameBoardHolder.addPlayer(player, new GameBoard());
        shotResultQueueHolder.addPlayer(player);
        turnHolder.addPlayer(player);
    }
}
