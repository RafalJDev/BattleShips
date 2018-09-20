package pl.krkteam.battleships.wait.opponent;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.wait.opponent.dto.WaiterResponseDTO;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
class WaitForOpponentController {
    private final RoomHolder roomHolder;

    public WaitForOpponentController(RoomHolder roomHolder) {
        this.roomHolder = roomHolder;
    }

    @GetMapping(value = "/room/opponent/present")
    public String isOpponentPresent(@RequestParam String playerName, @RequestParam String roomName) {
        Gson gson = new Gson();
        Player waitingPlayer = new Player(playerName);
        final WaiterResponseDTO waiterResponseDTO = roomHolder
                .isOpponentInRoom(roomName, waitingPlayer);
        log.debug("Room: " + roomName + ", is opponent in room: " + waiterResponseDTO.getResponse());

        return gson.toJson(waiterResponseDTO);
    }

}
