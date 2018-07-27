package pl.krkteam.battleships.room.holder;

import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultOkDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultWrongDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentAbsentDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentPresentDTO;
import pl.krkteam.battleships.wait.opponent.dto.WaiterResponseDTO;

import java.util.*;

public class Room {
    private String roomName;
    private Game game = new Game();
    private Set<Player> playerSet = new HashSet<>(2);
    private boolean isGameInitialized = false;

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
    
    public List<Player> getPlayerList(){
        List<Player> playerList=new ArrayList<>(playerSet);
        return Collections.unmodifiableList(playerList);
    }

    public synchronized WaiterResponseDTO areBothPlayers() {
        if (playerSet.size() == 2) {
            initializeGame();
            return new OpponentPresentDTO();
        }
        return new OpponentAbsentDTO();
    }

    private void initializeGame() {
        if (!isGameInitialized) {
            List<Player> playerList = new ArrayList<>(playerSet);
            game.initializeGame(playerList.get(0), playerList.get(1));
            isGameInitialized = true;
        }
    }

    synchronized boolean isPlayerBelongToRoom(Player player) {
        return playerSet.contains(player);
    }
}