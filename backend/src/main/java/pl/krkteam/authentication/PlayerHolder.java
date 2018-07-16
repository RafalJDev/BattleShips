package pl.krkteam.authentication;

import pl.krkteam.player.Player;

import java.util.*;

public class PlayerHolder {

    private Set<Player> players = new HashSet<>();

    public boolean addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        System.out.println("Player " + player.getName() + " has been added to PlayerHolder");
        return players.add(player);
    }

    public boolean isRegister(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        return players.contains(player);
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

}
