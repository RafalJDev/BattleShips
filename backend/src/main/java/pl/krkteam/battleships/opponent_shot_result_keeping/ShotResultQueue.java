package pl.krkteam.battleships.opponent_shot_result_keeping;

import java.util.LinkedList;
import java.util.Queue;

public class ShotResultQueue {

    private Queue<OpponentShotResult> shotResultQueue = new LinkedList<>();

    public boolean addShotResult(OpponentShotResult shotResult) {
        if (shotResult == null) {
            throw new IllegalArgumentException("Shot result cannot be null");
        }
        if (shotResult instanceof NoShoot) {
            return false;
        }
        return shotResultQueue.offer(shotResult);
    }

    public OpponentShotResult getOpponentShotResult() {
        if (shotResultQueue.isEmpty()) {
            return new NoShoot();
        }
        return shotResultQueue.remove();
    }

    public ShotResultQueue() {}

    public ShotResultQueue(ShotResultQueue shotResultQueue) {
        this.shotResultQueue = new LinkedList<>(shotResultQueue.shotResultQueue);
    }
}
