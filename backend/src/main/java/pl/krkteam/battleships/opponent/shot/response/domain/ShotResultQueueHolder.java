package pl.krkteam.battleships.opponent.shot.response.domain;

import lombok.extern.slf4j.Slf4j;
import pl.krkteam.battleships.common.domain.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
public class ShotResultQueueHolder {

    private Map<Player, ShotResultQueue> playerShotResultQueueMap = new HashMap<>();

    public ShotResultQueueHolder() {
    }

    public ShotResultQueueHolder(Player player1, Player player2) {
        initializeQueueHolder(player1, player2);
    }

    private void initializeQueueHolder(Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            String message = "Player cannot be null";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        playerShotResultQueueMap.put(player1, new ShotResultQueue());
        playerShotResultQueueMap.put(player2, new ShotResultQueue());
    }

    public boolean addPlayer(Player player) {
        if (player == null) {
            String message = "Player cannot be null";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        if (playerShotResultQueueMap.containsKey(player)) {
            return false;
        }
        playerShotResultQueueMap.put(player, new ShotResultQueue());
        return true;
    }

    public ShotResultQueue getShotResultQueue(Player player) {
        if (player == null) {
            String message = "Player cannot be null";
            throw new IllegalArgumentException(message);
        }
        if (!playerShotResultQueueMap.containsKey(player)) {
            String message = "Given player does not belong to this holder";
            throw new NoSuchElementException(message);
        }
        return playerShotResultQueueMap.get(player);
    }

}
