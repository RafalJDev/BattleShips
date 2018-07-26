package pl.krkteam.battleships.opponent.shot.response.controllers;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;
import pl.krkteam.battleships.room.holder.RoomHolder;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OpponentResultController {

    private final RoomHolder roomHolder;

    public OpponentResultController(RoomHolder roomHolder) {
        this.roomHolder = roomHolder;
    }

    @GetMapping(value = "/game/opponent/result")
    public String onShotOpponentResponse(@RequestParam String playerName, @RequestParam String roomName) {
        final Game game = getGameFromRoom(roomName);
        OpponentShotResult shotResult = getResponse(playerName, game);

        Gson gson = new Gson();
        return gson.toJson(shotResult);
    }

    private OpponentShotResult getResponse(String playerName, Game game) {
        Player opponentPlayer = new Player(playerName);
        return game.getShotResultFromQueue(opponentPlayer);
    }

    private Game getGameFromRoom(String roomName) {
        return roomHolder.getRoom(roomName).getGame();
    }
}
