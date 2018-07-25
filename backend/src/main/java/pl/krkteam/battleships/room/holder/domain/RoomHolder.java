package pl.krkteam.battleships.room.holder.domain;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.domain.dto.join.result.JoinResultDTO;

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
}
