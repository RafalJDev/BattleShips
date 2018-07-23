package pl.krkteam.battleships.turns.holding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.common.domain.player.Player;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TurnController {

    @Autowired
    private TurnHolder turnHolder;

    @GetMapping(value = "/game/player/isTurn")
    public String isTurnOfPlayer(@RequestParam String playerName) {
        Player player = new Player(playerName);
        boolean result = turnHolder.isTurnOfPlayer(player);
        String send = "{\"message\":" + "\"" + result + "\"" + " }";
        return send;
    }
    

}
