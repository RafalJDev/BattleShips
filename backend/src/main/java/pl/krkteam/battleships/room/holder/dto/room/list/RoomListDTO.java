package pl.krkteam.battleships.room.holder.dto.room.list;

import java.util.Arrays;

public class RoomListDTO {
    private RoomDTO[] roomDTOS;

    public RoomListDTO(RoomDTO[] roomDTOS) {
        this.roomDTOS = roomDTOS;
    }

    @Override
    public String toString() {
        return "RoomListDTO{" +
                "roomDTOS=" + (roomDTOS == null ? null : Arrays.asList(roomDTOS)) +
                '}';
    }
}
