package pl.krkteam.battleships.opponent.shot.response.domain;

import pl.krkteam.battleships.opponent.shot.response.dto.OpponentNoShot;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

import java.util.LinkedList;
import java.util.Queue;

public class ShotResultQueue {

    private Queue<OpponentShotResult> shotResultQueue = new LinkedList<>();

    public boolean addShotResult(OpponentShotResult shotResult) {
        if (shotResult == null) {
            throw new IllegalArgumentException("Shot result cannot be null");
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
