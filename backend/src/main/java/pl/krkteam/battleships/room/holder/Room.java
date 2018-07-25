package pl.krkteam.battleships.room.holder;

import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultOkDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultWrongDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentAbsentDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentPresentDTO;
import pl.krkteam.battleships.wait.opponent.dto.WaiterResponseDTO;

import java.util.HashSet;
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


    public synchronized WaiterResponseDTO areBothPlayers() {
        if (playerSet.size() == 2) {
            return new OpponentPresentDTO();
        }
        return new OpponentAbsentDTO();
    }

    public synchronized boolean isPlayerBelongToRoom(Player player) {
        return playerSet.contains(player);
    }
}
