package pl.krkteam.battleships.opponent_shot_result_keeping;

import pl.krkteam.battleships.common.domain.player.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ShotResultQueueHolder {

    private Map<Player, ShotResultQueue> playerShotResultQueueMap = new HashMap<>();

    private void initializeQueueHolder(Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        playerShotResultQueueMap.put(player1, new ShotResultQueue());
        playerShotResultQueueMap.put(player2, new ShotResultQueue());
    }

    public ShotResultQueueHolder(Player player1, Player player2) {
        initializeQueueHolder(player1, player2);
    }

    public ShotResultQueue getShotResultQueue(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (!playerShotResultQueueMap.containsKey(player)) {
            throw new NoSuchElementException("Given player does not belong to this holder");
        }
        return playerShotResultQueueMap.get(player);
    }
}