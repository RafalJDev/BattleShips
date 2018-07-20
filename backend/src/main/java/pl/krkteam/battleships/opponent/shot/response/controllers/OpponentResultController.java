package pl.krkteam.battleships.opponent.shot.response.controllers;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OpponentResultController {

    private final Game game;

    public OpponentResultController(Game game) {
        this.game = game;
    }

    @GetMapping(value = "/game/opponent/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onShotOpponentResponse(@RequestParam String playerName) {
        OpponentShotResult shotResult = getResponse(playerName);

        Gson gson = new Gson();
        return gson.toJson(shotResult);
    }


    private OpponentShotResult getResponse(String playerName) {
        Player opponentPlayer = new Player(playerName);
        return game.getShotResultQueueHolder().getShotResultQueue(opponentPlayer).getOpponentShotResult();
    }
}
