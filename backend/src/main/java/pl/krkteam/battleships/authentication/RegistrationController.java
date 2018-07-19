package pl.krkteam.battleships.authentication;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.PlayerDTO;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RegistrationController {

    @Autowired
    Game game;

    PlayerHolder playerHolder = new PlayerHolder();

    @PostMapping(value = "/login")
    public String signPlayer(@RequestBody String post) {
        System.out.println(post);

        Gson gson = new Gson();

        PlayerDTO playerDTO = gson.fromJson(post, PlayerDTO.class);

        System.out.println(playerDTO);

        Player player = PlayerConversionUtil.convertPlayerDTOtoPlayer(playerDTO);

        game.gameBoardHolder.addPlayer(player, new GameBoard());

        game.shotResultQueueHolder.addPlayer(player);

        boolean result = playerHolder.addPlayer(player);

        String send = "{\"result\":" + "\"" + result + "\"" + " }";
        return send;
    }

    @GetMapping(value = "/registered")
    public String getPlayers() {
        Set<Player> players = playerHolder.getPlayers();
        PlayerDTO[] playerDTOs = PlayerConversionUtil.convertPlayerToPlayerDTOs(players);

        Gson gson = new Gson();
        String result = gson.toJson(playerDTOs);
        System.out.println(result);
        return result;
    }

}
