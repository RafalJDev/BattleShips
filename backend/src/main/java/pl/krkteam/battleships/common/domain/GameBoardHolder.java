package pl.krkteam.battleships.common.domain;

import lombok.extern.slf4j.Slf4j;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.start.game.waiter.dto.ReadinessForPlayDTO;
import pl.krkteam.battleships.start.game.waiter.dto.ReadyForPlayDTO;
import pl.krkteam.battleships.start.game.waiter.dto.WaitForPlayDTO;

import java.util.*;

@Slf4j
public class GameBoardHolder {

    private Map<Player, GameBoard> playerGameBoardMap = new HashMap<>();

    boolean addPlayer(Player player, GameBoard gameBoard) {
        if (player == null) {
            String message = "Player cannot be null";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        if (gameBoard == null) {
            String message = "GameBoard cannot be null";
            log.error(message);
            throw new IllegalArgumentException(message);
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
            String message = "There are not enough players";
            log.error(message);
            throw new IndexOutOfBoundsException(message);
        }
        try {
            checkIfPlayerBelongThisBoardHolder(player);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }

        if (hasBothPlayersFleetPlaced()) {
            return new ReadyForPlayDTO();
        }
        return new WaitForPlayDTO();

    }

    private boolean hasBothPlayersFleetPlaced() {
        return playerGameBoardMap.values().stream().allMatch(GameBoard::isPlacedFleet);
    }

    public GameBoard getGameBoard(Player player) {
        if (player == null) {
            String message = "Player cannot be null";
            log.error(message);
            throw new IllegalArgumentException(message);
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
            String message = "Given player does not belong to this holder";
            log.error(message);
            throw new NoSuchElementException(message);
        }
    }
}
