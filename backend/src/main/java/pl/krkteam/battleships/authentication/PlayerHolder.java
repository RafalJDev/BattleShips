package pl.krkteam.battleships.authentication;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pl.krkteam.battleships.common.domain.player.Player;

import java.util.*;

public class PlayerHolder {

    private Set<Player> players = new HashSet<>();

    private static final Logger logger = LogManager.getLogger(PlayerHolder.class);

    public boolean addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        boolean result = players.add(player);
        if (result) {
            logger.info("Player " + player.getName() + " has been added to PlayerHolder");
        } else {
            logger.info("Player " + player.getName() + " is in PlayerHolder already");
        }
        return result;
    }

    public boolean isRegister(Player player) {
        if (player == null) {
            logger.error("Given player is null");
            throw new IllegalArgumentException("Player cannot be null");
        }
        return players.contains(player);
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

}
