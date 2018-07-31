package pl.krkteam.battleships.authentication;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.PlayerDTO;

import java.util.Arrays;
import java.util.Set;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RegistrationController {

    private final PlayerHolder playerHolder;

    public RegistrationController(PlayerHolder playerHolder) {
        this.playerHolder = playerHolder;
    }

    @PostMapping(value = "/login")
    public String signPlayer(@RequestBody String post) {
        Gson gson = new Gson();
        PlayerDTO playerDTO = gson.fromJson(post, PlayerDTO.class);
        log.debug("PlayerDTO: " + playerDTO);
        Player player = PlayerConversionUtil.convertPlayerDTOtoPlayer(playerDTO);

        PlayerResultAdderDTO playerResultAdderDTO = playerHolder.addPlayer(player);

        return gson.toJson(playerResultAdderDTO);
    }

    @GetMapping(value = "/registered")
    public String getPlayers() {
        Set<Player> players = playerHolder.getPlayers();
        PlayerDTO[] playerDTOs = PlayerConversionUtil.convertPlayerToPlayerDTOs(players);
        log.debug("Players list: " + Arrays.toString(playerDTOs));

        Gson gson = new Gson();
        String result = gson.toJson(playerDTOs);
        return result;
    }

}
