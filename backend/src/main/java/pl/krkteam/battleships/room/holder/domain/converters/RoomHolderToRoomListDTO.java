package pl.krkteam.battleships.room.holder.domain.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.krkteam.battleships.room.holder.domain.RoomHolder;
import pl.krkteam.battleships.room.holder.domain.dto.room.list.RoomDTO;
import pl.krkteam.battleships.room.holder.domain.dto.room.list.RoomListDTO;

import java.util.ArrayList;
import java.util.List;


@Component
public class RoomHolderToRoomListDTO implements Converter<RoomHolder, RoomListDTO> {

    @Override
    public RoomListDTO convert(RoomHolder source) {
        if (source == null) {
            return null;
        }

        List<RoomDTO> roomList = new ArrayList<>();
        source.getRoomList().forEach(room -> {
            roomList.add(new RoomDTO(room.getRoomName()));
        });
        RoomDTO[] roomDTOS = new RoomDTO[source.getRoomList().size()];
        roomDTOS = roomList.toArray(roomDTOS);

        return new RoomListDTO(roomDTOS);
    }
}
