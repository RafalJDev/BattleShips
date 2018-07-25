package pl.krkteam.battleships.authentication;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.player.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class PlayerHolder {

    private Set<Player> players = new HashSet<>();

    private static final Logger logger = LogManager.getLogger(PlayerHolder.class);

    public PlayerResultAdderDTO addPlayer(Player player) {
        if (player == null) {
            logger.error("Given player is null");
            throw new IllegalArgumentException("Player cannot be null");
        }
        return new PlayerResultAdderDTO(players.add(player));
    }

    public boolean isRegistered(Player player) {
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
