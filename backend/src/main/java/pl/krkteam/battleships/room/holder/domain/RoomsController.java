package pl.krkteam.battleships.room.holder.domain;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.domain.converters.RoomHolderToRoomListDTO;
import pl.krkteam.battleships.room.holder.domain.dto.room.list.RoomListDTO;
import pl.krkteam.battleships.room.holder.domain.dto.join.result.JoinResultDTO;
import pl.krkteam.battleships.room.holder.domain.dto.join.result.JoinResultWrongDTO;

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

    @PostMapping(value = "room/join")
    public String joinRoom(@RequestParam String playerName, @RequestParam String roomNumber) {
        Gson gson = new Gson();

        int roomNumberInt;
        try {
            roomNumberInt = Integer.valueOf(roomNumber);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return gson.toJson(new JoinResultWrongDTO());
        }

        Player joiningPlayer = new Player(playerName);

        JoinResultDTO joinResultDTO = roomHolder.joinPlayer(roomNumberInt, joiningPlayer);

        return gson.toJson(joinResultDTO);
    }


}
