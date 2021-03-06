package pl.krkteam.battleships.start.game.waiter;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.GameBoardHolder;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.start.game.waiter.dto.ReadinessForPlayDTO;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class WaitForStartGameController {

    private final RoomHolder roomHolder;

    public WaitForStartGameController(RoomHolder roomHolder) {
        this.roomHolder = roomHolder;
    }

    @GetMapping(value = "/room/game/start")
    public String canWeStartPlay(@RequestParam String playerName, @RequestParam String roomName) {
        final GameBoardHolder gameBoardHolder = getGameBoardHolder(roomName);

        Player askingPlayer = new Player(playerName);
        final ReadinessForPlayDTO readinessForPlayDTO = gameBoardHolder.areBothFleetsValid(askingPlayer);
        log.debug("Room: " + roomName + ", players readiness for game: " + readinessForPlayDTO.getResult());

        Gson gson = new Gson();
        return gson.toJson(readinessForPlayDTO);
    }

    private GameBoardHolder getGameBoardHolder(String roomName) {
        final Game game = getGameFromRoom(roomName);
        return game.getGameBoardHolder();
    }

    private Game getGameFromRoom(String roomName) {
        return roomHolder.getRoom(roomName).getGame();
    }
}
