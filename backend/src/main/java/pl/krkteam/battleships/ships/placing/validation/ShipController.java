package pl.krkteam.battleships.ships.placing.validation;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.domain.GameBoardHolder;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.PlacingValidationResultDTO;
import pl.krkteam.battleships.common.dto.ShipFromFronted;
import pl.krkteam.battleships.common.dto.ShipHolderDTO;
import pl.krkteam.battleships.ships.placing.validation.converters.ShipsToShipHolder;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipHolderFromJson;
import pl.krkteam.battleships.ships.placing.validation.services.ShipsPlacingValidatorService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ShipController {

    private final ShipsPlacingValidatorService shipsLocationValidatorService;
    private final ShipsToShipHolder shipsToShipHolder;

    public ShipController(
            ShipsPlacingValidatorService shipsLocationValidatorService,
            ShipsToShipHolder shipsToShipHolder) {
        this.shipsLocationValidatorService = shipsLocationValidatorService;
        this.shipsToShipHolder = shipsToShipHolder;
    }

    @Autowired
    Game game;

    @PostMapping(value = "/post/ships")
    public String communicateWithAngularByPostingShip(@RequestBody String post) {

        Gson gson = new Gson();

        ShipFromFronted shipFromFronted = gson.fromJson(post, ShipFromFronted.class);

        System.out.println(shipFromFronted);

        return "{\"message\": \"Cry Germoney!\"}";
    }

    @PostMapping(value = "/ships")
    public String validateAndSaveShips(@RequestBody String post, @RequestParam String playerName) {

        Gson gson = new Gson();

        Player player = new Player(playerName);

        GameBoard playerGameBoard = game.gameBoardHolder.getGameBoard(player);
        playerGameBoard.reset();

        ShipHolderDTO shipHolderDTO = gson.fromJson(post, ShipHolderDTO.class);
        final ShipHolderFromJson shipHolderFromJson = shipsToShipHolder.convert(shipHolderDTO);

        final PlacingValidationResultDTO placingValidationResultDTO =
                shipsLocationValidatorService.validateShipLocation(shipHolderFromJson, playerGameBoard);

        if (placingValidationResultDTO.getResult().equals(PlacingValidationResultDTO.Result.WRONG)) {
            playerGameBoard.reset();
            System.out.println("Validation WRONG");
        }

        System.out.println(placingValidationResultDTO.getResult());

        return gson.toJson(placingValidationResultDTO);
    }
}
