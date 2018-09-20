package pl.krkteam.battleships.turns.holding;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.turns.holding.dto.TurnResultDTO;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
class TurnController {

    private final RoomHolder roomHolder;

    public TurnController(RoomHolder roomHolder) {
        this.roomHolder = roomHolder;
    }

    @GetMapping(value = "/game/player/isTurn")
    public String isTurnOfPlayer(@RequestParam String playerName, @RequestParam String roomName) {
        final Game game = getGameFromRoom(roomName);
        TurnHolder turnHolder = game.getTurnHolder();

        Player player = new Player(playerName);
        boolean result = turnHolder.isTurnOfPlayer(player);
        Gson gson = new Gson();
        return gson.toJson(new TurnResultDTO(result));
    }

    private Game getGameFromRoom(String roomName) {
        return roomHolder.getRoom(roomName).getGame();
    }

}
