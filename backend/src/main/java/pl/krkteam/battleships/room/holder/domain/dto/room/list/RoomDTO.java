package pl.krkteam.battleships.room.holder.domain.dto.room.list;

public class RoomDTO {
    private String roomName;

    public RoomDTO(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomName='" + roomName + '\'' +
                '}';
    }
}
