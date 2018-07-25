package pl.krkteam.battleships.room.holder.domain.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.krkteam.battleships.room.holder.domain.RoomHolder;
import pl.krkteam.battleships.room.holder.domain.dto.RoomListDTO;

import java.util.ArrayList;
import java.util.List;


@Component
public class RoomHolderToRoomListDTO implements Converter<RoomHolder, RoomListDTO> {

    @Override
    public RoomListDTO convert(RoomHolder source) {
        if (source == null) {
            return null;
        }

        List<String> roomList = new ArrayList<>();
        source.getRoomList().forEach(room -> roomList.add(room.getRoomName()));
        String[] roomNameList = new String[roomList.size()];
        roomNameList = roomList.toArray(roomNameList);

        return new RoomListDTO(roomNameList);
    }
}
