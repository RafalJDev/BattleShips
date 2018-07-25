package pl.krkteam.battleships.authentication;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.PlayerDTO;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RegistrationController {

    private final Game game;
    private final PlayerHolder playerHolder;

    public RegistrationController(Game game, PlayerHolder playerHolder) {
        this.game = game;
        this.playerHolder = playerHolder;
    }

    @PostMapping(value = "/login")
    public String signPlayer(@RequestBody String post) {

        Gson gson = new Gson();
        PlayerDTO playerDTO = gson.fromJson(post, PlayerDTO.class);
        Player player = PlayerConversionUtil.convertPlayerDTOtoPlayer(playerDTO);

        PlayerResultAdderDTO playerResultAdderDTO = playerHolder.addPlayer(player);
//        game.addPlayer(player);

        String addResultJson = gson.toJson(playerResultAdderDTO);
        return addResultJson;
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
