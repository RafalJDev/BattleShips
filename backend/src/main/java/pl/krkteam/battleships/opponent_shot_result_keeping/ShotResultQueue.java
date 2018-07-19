package pl.krkteam.battleships.opponent_shot_result_keeping;

import pl.krkteam.battleships.result.models.dto.OpponentNoShot;
import pl.krkteam.battleships.result.models.dto.OpponentShotResult;

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
