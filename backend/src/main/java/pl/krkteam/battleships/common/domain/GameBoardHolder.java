package pl.krkteam.battleships.common.domain;

import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.start.game.waiter.dto.ReadinessForPlayDTO;
import pl.krkteam.battleships.start.game.waiter.dto.ReadyForPlayDTO;
import pl.krkteam.battleships.start.game.waiter.dto.WaitForPlayDTO;

import java.util.*;

public class GameBoardHolder {

    private Map<Player, GameBoard> playerGameBoardMap = new HashMap<>();

    boolean addPlayer(Player player, GameBoard gameBoard) {
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

    boolean addPlayer(Player player) {
        return addPlayer(player, new GameBoard());
    }

    public ReadinessForPlayDTO areBothFleetsValid(Player player) {
        if (playerGameBoardMap.size() != 2) {
            throw new IndexOutOfBoundsException("There are not enough players");
        }
        checkIfPlayerBelongThisBoardHolder(player);

        if (playerGameBoardMap.values().stream().allMatch(GameBoard::isPlacedFleet)) {
            return new ReadyForPlayDTO();
        }
        return new WaitForPlayDTO();

    }

    public GameBoard getGameBoard(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        checkIfPlayerBelongThisBoardHolder(player);

        return playerGameBoardMap.get(player);
    }

    public Player getOpponentPlayer(Player player) {
        Set<Player> playerSet = playerGameBoardMap.keySet();
        List<Player> playerList = new ArrayList<>(playerSet);
        playerList.remove(player);
        return playerList.get(0);
    }

    private void checkIfPlayerBelongThisBoardHolder(Player player) {
        if (!playerGameBoardMap.containsKey(player)) {
            throw new NoSuchElementException("Given player does not belong to this holder");
        }
    }
}
