package pl.krkteam.battleships.room.holder.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RoomHolder {
    private List<Room> roomList = new ArrayList<>();

    public List<Room> getRoomList() {
        return Collections.unmodifiableList(roomList);
    }
}
