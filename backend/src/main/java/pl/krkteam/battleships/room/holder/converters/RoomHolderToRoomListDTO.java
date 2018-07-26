package pl.krkteam.battleships.room.holder.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.krkteam.battleships.room.holder.Room;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.room.holder.dto.room.list.RoomDTO;
import pl.krkteam.battleships.room.holder.dto.room.list.RoomListDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomHolderToRoomListDTO implements Converter<RoomHolder, RoomListDTO> {

    @Override
    public RoomListDTO convert(RoomHolder source) {
        if (source == null) {
            return null;
        }

        List<Room> sourceRoomList = source.getRoomList();

        List<RoomDTO> roomList = new ArrayList<>();
        sourceRoomList.forEach(room -> roomList.add(new RoomDTO(room.getRoomName())));

        RoomDTO[] roomDTOS = new RoomDTO[sourceRoomList.size()];
        roomDTOS = roomList.toArray(roomDTOS);

        return new RoomListDTO(roomDTOS);
    }
}
