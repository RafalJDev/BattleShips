package pl.krkteam.battleships.room.holder.domain.dto.room.list;

import java.util.Arrays;

public class RoomListDTO {
    private RoomDTO[] roomName;

    public RoomListDTO(RoomDTO[] roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "RoomListDTO{" +
                "roomName=" + (roomName == null ? null : Arrays.asList(roomName)) +
                '}';
    }
}
