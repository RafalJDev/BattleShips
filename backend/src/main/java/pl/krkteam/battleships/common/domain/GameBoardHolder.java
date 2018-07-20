package pl.krkteam.battleships.common.domain;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class GameBoardHolder {

    private Map<Player, GameBoard> playerGameBoardMap = new HashMap<>();

    public boolean addPlayer(Player player, GameBoard gameBoard) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (gameBoard == null) {
            throw new IllegalArgumentException("GameBoard cannot be null");
        }
        if (playerGameBoardMap.containsKey(player)) {
            return false;
        }
        playerGameBoardMap.put(player, gameBoard);
        return true;
    }

    public GameBoard getGameBoard(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (!playerGameBoardMap.containsKey(player)) {
            throw new NoSuchElementException("Given player does not belong to this holder");
        }
        return playerGameBoardMap.get(player);
    }
}
