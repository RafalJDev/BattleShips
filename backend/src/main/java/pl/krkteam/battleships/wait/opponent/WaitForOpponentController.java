package pl.krkteam.battleships.wait.opponent;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.wait.opponent.dto.OpponentAbsentDTO;
import pl.krkteam.battleships.wait.opponent.dto.WaiterResponseDTO;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class WaitForOpponentController {
    private final RoomHolder roomHolder;

    public WaitForOpponentController(RoomHolder roomHolder) {
        this.roomHolder = roomHolder;
    }

    @PostMapping(value = "room/opponent/present")
    public String isOpponentPresent(@RequestParam String playerName, @RequestParam String roomNumber) {

        Gson gson = new Gson();

        int roomNumberInt;
        try {
            roomNumberInt = Integer.valueOf(roomNumber);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return gson.toJson(new OpponentAbsentDTO());
        }

        Player waitingPlayer = new Player(playerName);

        final WaiterResponseDTO waiterResponseDTO = roomHolder.isOpponentInRoom(roomNumberInt, waitingPlayer);

        return gson.toJson(waiterResponseDTO);
    }

}
