package pl.krkteam.battleships.wait.opponent;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.wait.opponent.dto.WaiterResponseDTO;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class WaitForOpponentController {
    private final RoomHolder roomHolder;

    public WaitForOpponentController(RoomHolder roomHolder) {
        this.roomHolder = roomHolder;
    }

    @GetMapping(value = "room/opponent/present")
    public String isOpponentPresent(@RequestParam String playerName, @RequestParam String roomName) {
        Gson gson = new Gson();
        Player waitingPlayer = new Player(playerName);
        final WaiterResponseDTO waiterResponseDTO = roomHolder
                .isOpponentInRoom(roomName, waitingPlayer);
        
        return gson.toJson(waiterResponseDTO);
    }

}
