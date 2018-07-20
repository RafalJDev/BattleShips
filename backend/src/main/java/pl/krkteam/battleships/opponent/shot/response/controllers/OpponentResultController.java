package pl.krkteam.battleships.opponent.shot.response.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  Game game;
  
  @GetMapping(value = "/game/opponent/result", produces = MediaType.APPLICATION_JSON_VALUE)
  public String sayHello(@RequestParam String playerName) {

    Player player = new Player(playerName);

    OpponentShotResult shotResult = game.shotResultQueueHolder.getShotResultQueue(player).getOpponentShotResult();

    Gson gson = new Gson();
    String opponentShotResultJson = gson.toJson(shotResult);
    System.out.println(opponentShotResultJson);
    
    return opponentShotResultJson;
  }
}
