package pl.krkteam.battleships.authentication;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.player.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class PlayerHolder {
    
    private Set<Player> players = new HashSet<>();
    
    public PlayerResultAdderDTO addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        System.out.println("Player " + player.getName() + " has been added to PlayerHolder");
        return new PlayerResultAdderDTO(players.add(player));
    }
    
    public boolean isRegistered(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        return players.contains(player);
    }
    
    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }
    
}
