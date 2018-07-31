package pl.krkteam.battleships.opponent.shot.response.domain;

import lombok.extern.slf4j.Slf4j;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentNoShot;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class ShotResultQueue {

    private Queue<OpponentShotResult> shotResultQueue = new LinkedList<>();

    public boolean addShotResult(OpponentShotResult shotResult) {
        if (shotResult == null) {
            String message = "Shot result cannot be null";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        if (shotResult instanceof OpponentNoShot) {
            return false;
        }
        return shotResultQueue.offer(shotResult);
    }

    public OpponentShotResult getOpponentShotResult() {
        if (shotResultQueue.isEmpty()) {
            return new OpponentNoShot();
        }
        return shotResultQueue.remove();
    }
}
