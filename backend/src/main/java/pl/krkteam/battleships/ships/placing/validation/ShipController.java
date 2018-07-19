package pl.krkteam.battleships.ships.placing.validation;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.domain.GameBoardHolder;
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
    private final GameBoardHolder gameBoardHolder;

    public ShipController(
            ShipsPlacingValidatorService shipsLocationValidatorService,
            ShipsToShipHolder shipsToShipHolder, GameBoardHolder gameBoardHolder) {
        this.shipsLocationValidatorService = shipsLocationValidatorService;
        this.shipsToShipHolder = shipsToShipHolder;
        this.gameBoardHolder = gameBoardHolder;
    }

    @PostMapping(value = "/post/ships")
    public String communicateWithAngularByPostingShip(@RequestBody String post) {

        Gson gson = new Gson();

        ShipFromFronted shipFromFronted = gson.fromJson(post, ShipFromFronted.class);

        System.out.println(shipFromFronted);

        return "{\"message\": \"Cry Germoney!\"}";
    }

    @PostMapping(value = "/ships")
    public String validateAndSaveShips(@RequestBody String post) {

        Gson gson = new Gson();

        GameBoard playerGameBoard = gameBoardHolder.getGameBoard(gameBoardHolder.player);
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
