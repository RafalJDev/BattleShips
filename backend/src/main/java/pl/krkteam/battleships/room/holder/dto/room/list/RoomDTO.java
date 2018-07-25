package pl.krkteam.battleships.room.holder.dto.room.list;

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
