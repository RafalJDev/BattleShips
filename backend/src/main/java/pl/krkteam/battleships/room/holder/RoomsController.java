package pl.krkteam.battleships.room.holder;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.converters.RoomHolderToRoomListDTO;
import pl.krkteam.battleships.room.holder.dto.create.room.CreateResultDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultDTO;
import pl.krkteam.battleships.room.holder.dto.room.list.RoomDTO;
import pl.krkteam.battleships.room.holder.dto.room.list.RoomListDTO;

@Slf4j
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

    @GetMapping(value = "/room/list")
    public String getRoomList() {

        final RoomListDTO roomListDTO = roomHolderToRoomListDTO.convert(roomHolder);
        log.debug(roomListDTO.toString());

        Gson gson = new Gson();
        return gson.toJson(roomListDTO);
    }

    @GetMapping(value = "/room/join")
    public String joinRoom(@RequestParam String playerName, @RequestParam String roomName) {
        Gson gson = new Gson();

        Player joiningPlayer = new Player(playerName);

        JoinResultDTO joinResultDTO = roomHolder.joinPlayer(roomName, joiningPlayer);
        log.info("Join result: " + joinResultDTO.getResult() + " to room: " + roomName + ", player: " + playerName);

        return gson.toJson(joinResultDTO);
    }

    @PostMapping(value = "/room/create")
    public String createRoom(@RequestBody String roomJson, @RequestParam String playerName) {
        Gson gson = new Gson();

        RoomDTO roomDTO = gson.fromJson(roomJson, RoomDTO.class);
        Player roomCreatorPlayer = new Player(playerName);

        final String roomName = roomDTO.getRoomName();
        CreateResultDTO createResultDTO = roomHolder.createRoomAndJoinPlayer(roomCreatorPlayer, roomName);
        log.info("Creating room result: " + createResultDTO.getResult()
                + ", room name: " + roomName + ", by player: " + playerName);

        return gson.toJson(createResultDTO);
    }

}
