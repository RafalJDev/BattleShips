package pl.krkteam.battleships.common.domain;

import pl.krkteam.battleships.common.domain.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class GameBoardHolder {

    private Map<Player, GameBoard> playerGameBoardMap = new HashMap<>();

    private void initializeGameBoards(Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        playerGameBoardMap.put(player1, new GameBoard());
        playerGameBoardMap.put(player2, new GameBoard());
    }

    public GameBoardHolder(Player player1, Player player2) {
        initializeGameBoards(player1, player2);
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
