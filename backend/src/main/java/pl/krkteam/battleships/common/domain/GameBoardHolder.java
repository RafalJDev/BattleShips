package pl.krkteam.battleships.common.domain;

import pl.krkteam.battleships.common.domain.player.Player;

import java.util.*;

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

    public Player getOpponentPlayer(Player player) {
        Set<Player> playerSet = playerGameBoardMap.keySet();
        List<Player> playerList = new ArrayList<>(playerSet);
        playerList.remove(player);
        return playerList.get(0);
    }
}
