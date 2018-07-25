package pl.krkteam.battleships.room.holder;

import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultOkDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultWrongDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentAbsentDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentPresentDTO;
import pl.krkteam.battleships.wait.opponent.dto.WaiterResponseDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room {
    private String roomName;
    private Game game = new Game();
    private Set<Player> playerSet = new HashSet<>(2);

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public synchronized JoinResultDTO joinPlayer(Player player) {
        if (playerSet.size() < 2) {
            playerSet.add(player);
            return new JoinResultOkDTO();
        }
        return new JoinResultWrongDTO();
    }

    public Game getGame() {
        return game;
    }

    public synchronized WaiterResponseDTO areBothPlayers() {
        if (playerSet.size() == 2) {
            initializeGame();
            return new OpponentPresentDTO();
        }
        return new OpponentAbsentDTO();
    }

    private void initializeGame() {
        List<Player> playerList = new ArrayList<>(playerSet);
        game.initializeGame(playerList.get(0), playerList.get(1));
    }

    public synchronized boolean isPlayerBelongToRoom(Player player) {
        return playerSet.contains(player);
    }
}
