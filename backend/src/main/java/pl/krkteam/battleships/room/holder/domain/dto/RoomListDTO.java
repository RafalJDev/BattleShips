package pl.krkteam.battleships.room.holder.domain.dto;

import java.util.Arrays;

public class RoomListDTO {
    private String[] roomName;

    public RoomListDTO(String[] roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "RoomListDTO{" +
                "roomName=" + (roomName == null ? null : Arrays.asList(roomName)) +
                '}';
    }
}
