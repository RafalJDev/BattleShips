package pl.krkteam.battleships.room.holder;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentAbsentDTO;
import pl.krkteam.battleships.wait.opponent.dto.WaiterResponseDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RoomHolder {
    private List<Room> roomList = new ArrayList<>();

    public List<Room> getRoomList() {
        return Collections.unmodifiableList(roomList);
    }

    public JoinResultDTO joinPlayer(int roomNumberInt, Player player) {
        final Room room = roomList.get(roomNumberInt);
        return room.joinPlayer(player);
    }

    public WaiterResponseDTO isOpponentInRoom(int roomNumberInt, Player player){
        final Room room = roomList.get(roomNumberInt);
        if (room.isPlayerBelongToRoom(player)){
            return new OpponentAbsentDTO();
        }
        return room.areBothPlayers();
    }


}
