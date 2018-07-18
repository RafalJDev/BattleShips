package pl.krkteam.battleships.result.controller;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.result.models.dto.OpponentNoShot;
import pl.krkteam.battleships.result.models.dto.OpponentShotHit;
import pl.krkteam.battleships.result.models.dto.OpponentShotMiss;
import pl.krkteam.battleships.result.models.dto.OpponentShotResult;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OpponentResultController {
  
  int count = 0;
  
  OpponentShotResult opponentShotResult;
  
  @GetMapping(value = "/game/opponent/result", produces = MediaType.APPLICATION_JSON_VALUE)
  public String sayHello() {
    count++;
    
    opponentShotResult = new OpponentShotHit(1, 1);
    if (count >= 2 && count <= 3) {
      opponentShotResult = new OpponentNoShot();
    }
    
    if (count == 5) {
      opponentShotResult = new OpponentShotMiss(2, 1);
      count = 0;
    }
    
    Gson gson = new Gson();
    String opponentShotResultJson = gson.toJson(opponentShotResult);
    System.out.println(opponentShotResultJson);
    
    return opponentShotResultJson;
  }
}
