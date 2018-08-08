package pl.krkteam.battleships.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.player.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class PlayerHolder {

    private Set<Player> players = new HashSet<>();

    public PlayerResultAdderDTO addPlayer(Player player) {
        if (player == null) {
            log.error("Player cannot be null");
            throw new IllegalArgumentException("Player cannot be null");
        }
        log.info("Player " + player.getPlayerName() + " has been added to PlayerHolder.");
        return new PlayerResultAdderDTO(players.add(player));
    }

    public boolean isRegistered(Player player) {
        if (player == null) {
            log.error("Given player is null");
            throw new IllegalArgumentException("Player cannot be null");
        }
        return players.contains(player);
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

}
