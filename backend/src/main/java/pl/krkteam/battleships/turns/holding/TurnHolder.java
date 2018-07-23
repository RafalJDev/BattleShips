package pl.krkteam.battleships.turns.holding;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.shooting.dto.result.ShotResultDTO;

import java.util.*;

@Component
public class TurnHolder {

    volatile private Player currentPlayer;

    private final Player firstPlayer;

    private final Player secondPlayer;

    private volatile boolean gameHasBeenStarted = false;

    public TurnHolder(Player initialPlayer, Player opponent) {
        if (initialPlayer == null || opponent == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        firstPlayer = initialPlayer;
        secondPlayer = opponent;
        currentPlayer = initialPlayer;
    }

    public synchronized void choseFirst(Player pretendToBeFirst) {
        if (gameHasBeenStarted) {
            throw new IllegalStateException("First Player cannot be set - game already is in progress");
        }
        if (!pretendToBeFirst.equals(firstPlayer) && !pretendToBeFirst.equals(secondPlayer)) {
            throw new NoSuchElementException("Player is not registered in this holder");
        }
        currentPlayer = pretendToBeFirst;
    }

    public synchronized void addShotResult(Player shootingPlayer, ShotResultDTO shotResult) {
        if (shotResult == null) {
            throw new IllegalArgumentException("ShotResult cannot be null");
        }
        if (!shootingPlayer.equals(firstPlayer) && !shootingPlayer.equals(secondPlayer)) {
            throw new NoSuchElementException("Player is not registered in this holder");
        }
        switch (shotResult.getMessage()) {
            case "ResultHit":
            case "ResultSunk":
                currentPlayer = shootingPlayer;
                break;
            case "ResultMiss":
            case "WrongShot":
                currentPlayer = shootingPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
        }
        gameHasBeenStarted = true;
    }

    public synchronized Player getCurrentPlayer() {
        return currentPlayer;
    }

    public synchronized boolean isTurnOfPlayer(Player player) {
        if (!player.equals(firstPlayer) && !player.equals(secondPlayer)) {
            throw new NoSuchElementException("Player is not registered in this holder");
        }
        return currentPlayer.equals(player);
    }

}
