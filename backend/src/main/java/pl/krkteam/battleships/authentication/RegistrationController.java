package pl.krkteam.battleships.authentication;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.PlayerDTO;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RegistrationController {

    private static final Logger logger = LogManager.getLogger(RegistrationController.class);
    private final PlayerHolder playerHolder;

    public RegistrationController(PlayerHolder playerHolder) {
        this.playerHolder = playerHolder;
    }

    @PostMapping(value = "/login")
    public String signPlayer(@RequestBody String post) {
        Gson gson = new Gson();
        PlayerDTO playerDTO = gson.fromJson(post, PlayerDTO.class);
        logger.debug("PlayerDTO: " + playerDTO);
        Player player = PlayerConversionUtil.convertPlayerDTOtoPlayer(playerDTO);

        PlayerResultAdderDTO playerResultAdderDTO = playerHolder.addPlayer(player);

        return gson.toJson(playerResultAdderDTO);
    }

    @GetMapping(value = "/registered")
    public String getPlayers() {
        Set<Player> players = playerHolder.getPlayers();
        PlayerDTO[] playerDTOs = PlayerConversionUtil.convertPlayerToPlayerDTOs(players);

        Gson gson = new Gson();
        String result = gson.toJson(playerDTOs);
        logger.debug("Players list " + playerDTOs);
        return result;
    }

}
