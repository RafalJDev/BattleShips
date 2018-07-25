package pl.krkteam.battleships.ships.placing.validation;

import com.google.gson.Gson;
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

    private static final Logger logger = LogManager.getLogger(ShipController.class);

    private final ShipsPlacingValidatorService shipsLocationValidatorService;
    private final ShipsToShipHolder shipsToShipHolder;
    private final Game game;


    public ShipController(
            ShipsPlacingValidatorService shipsLocationValidatorService,
            ShipsToShipHolder shipsToShipHolder, Game game) {
        this.shipsLocationValidatorService = shipsLocationValidatorService;
        this.shipsToShipHolder = shipsToShipHolder;
        this.game = game;
    }

    @PostMapping(value = "/post/ships")
    public String communicateWithAngularByPostingShip(@RequestBody String post) {

        Gson gson = new Gson();

        ShipFromFronted shipFromFronted = gson.fromJson(post, ShipFromFronted.class);

        logger.info("One ship has been posted: " + shipFromFronted);

        return "{\"message\": \"Cry Germoney!\"}";
    }

    @PostMapping(value = "/ships")
    public String validateAndSaveShips(@RequestBody String shipsJson, @RequestParam String playerName) {

        GameBoard playerGameBoard = prepareGameBoard(playerName);

        final ShipHolderFromJson shipHolderFromJson = createShipHolderFromJson(shipsJson);

        final PlacingValidationResultDTO placingValidationResultDTO =
                shipsLocationValidatorService.validateShipLocation(shipHolderFromJson, playerGameBoard);

        resetGameBoardIfValidationFailed(placingValidationResultDTO, playerGameBoard);

        Gson gson = new Gson();
        return gson.toJson(placingValidationResultDTO);
    }

    private ShipHolderFromJson createShipHolderFromJson(String postData) {
        Gson gson = new Gson();
        final ShipHolderDTO shipHolderDTO = gson.fromJson(postData, ShipHolderDTO.class);
        return shipsToShipHolder.convert(shipHolderDTO);
    }

    private GameBoard prepareGameBoard(String playerName) {
        Player player = new Player(playerName);
        final GameBoardHolder gameBoardHolder = game.getGameBoardHolder();
        GameBoard playerGameBoard = gameBoardHolder.getGameBoard(player);
        playerGameBoard.reset();
        return playerGameBoard;
    }

    private void resetGameBoardIfValidationFailed(PlacingValidationResultDTO placingValidationResultDTO,
                                                  GameBoard playerGameBoard) {
        if (placingValidationResultDTO.getResult().equals(PlacingValidationResultDTO.Result.WRONG)) {
            playerGameBoard.reset();
            logger.error("Validation WRONG");
        }

        logger.info("Validation shot result from player " + playerName + ": " + placingValidationResultDTO.getResult());

        return gson.toJson(placingValidationResultDTO);
    }
}
