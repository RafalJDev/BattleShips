package pl.krkteam.battleships.room.holder;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.dto.create.room.CreateResultDTO;
import pl.krkteam.battleships.room.holder.dto.create.room.CreateRoomOkDTO;
import pl.krkteam.battleships.room.holder.dto.create.room.CreateRoomWrongDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentAbsentDTO;
import pl.krkteam.battleships.wait.opponent.dto.WaiterResponseDTO;

import java.util.*;

@Component
public class RoomHolder {
    private Map<String, Room> roomNameToRoomMap = Collections.synchronizedMap(new LinkedHashMap<>());

    public List<Room> getRoomList() {
        List<Room> roomList = new ArrayList<>(roomNameToRoomMap.values());
        return Collections.unmodifiableList(roomList);
    }

    JoinResultDTO joinPlayer(String roomName, Player player) {
        final Room room = roomNameToRoomMap.get(roomName);
        return room.joinPlayer(player);
    }

    public WaiterResponseDTO isOpponentInRoom(String roomName, Player player) {
        final Room room = roomNameToRoomMap.get(roomName);
        if (!room.isPlayerBelongToRoom(player)) {
            return new OpponentAbsentDTO();
        }
        return room.areBothPlayers();
    }

    public Room getRoom(String roomName) {
        final Room room = roomNameToRoomMap.get(roomName);
        if (room == null) {
            throw new NoSuchElementException();
        }
        return room;
    }

    public CreateResultDTO createRoomAndJoinPlayer(Player player, String roomName) {
        if (roomNameToRoomMap.containsKey(roomName)) {
            return new CreateRoomWrongDTO();
        }

        Room room = new Room(roomName);
        roomNameToRoomMap.put(roomName, room);

        room.joinPlayer(player);
        return new CreateRoomOkDTO();
    }
}
