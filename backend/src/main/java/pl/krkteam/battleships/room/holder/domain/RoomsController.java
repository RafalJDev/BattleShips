package pl.krkteam.battleships.room.holder.domain;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.room.holder.domain.converters.RoomHolderToRoomListDTO;
import pl.krkteam.battleships.room.holder.domain.dto.RoomListDTO;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RoomsController {


    private final RoomHolder roomHolder;
    private final RoomHolderToRoomListDTO roomHolderToRoomListDTO;


    public RoomsController(RoomHolder roomHolder,
                           RoomHolderToRoomListDTO roomHolderToRoomListDTO) {
        this.roomHolder = roomHolder;
        this.roomHolderToRoomListDTO = roomHolderToRoomListDTO;
    }

    @PostMapping(value = "room/list")
    public String getRoomList() {

        final RoomListDTO roomListDTO = roomHolderToRoomListDTO.convert(roomHolder);

        Gson gson = new Gson();
        return gson.toJson(roomListDTO);
    }

}
