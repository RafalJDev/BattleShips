package pl.krkteam.battleships.ships.placing.validation;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.domain.GameBoardHolder;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.PlacingValidationResultDTO;
import pl.krkteam.battleships.common.dto.ShipHolderDTO;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.ships.placing.validation.converters.ShipsToShipHolder;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipHolderFromJson;
import pl.krkteam.battleships.ships.placing.validation.services.ShipsPlacingValidatorService;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
class ShipController {

    private final ShipsPlacingValidatorService shipsLocationValidatorService;
    private final ShipsToShipHolder shipsToShipHolder;
    private final RoomHolder roomHolder;

    ShipController(ShipsPlacingValidatorService shipsLocationValidatorService,
                          ShipsToShipHolder shipsToShipHolder,
                          RoomHolder roomHolder) {
        this.shipsLocationValidatorService = shipsLocationValidatorService;
        this.shipsToShipHolder = shipsToShipHolder;
        this.roomHolder = roomHolder;
    }

    @PostMapping(value = "/ships")
    String validateAndSaveShips(@RequestBody String shipsJson,
                                       @RequestParam String playerName,
                                       @RequestParam String roomName) {

        GameBoard playerGameBoard = prepareGameBoard(playerName, roomName);
        log.info("Player: " + playerName + " in room: " + roomName + " sent fleet.");

        final ShipHolderFromJson shipHolderFromJson = createShipHolderFromJson(shipsJson);

        final PlacingValidationResultDTO placingValidationResultDTO = shipsLocationValidatorService
                .validateShipLocation(shipHolderFromJson, playerGameBoard);
        log.info("Fleet validation result: " + placingValidationResultDTO.getResult());

        fleetPlacementHandler(placingValidationResultDTO, playerGameBoard);

        Gson gson = new Gson();
        return gson.toJson(placingValidationResultDTO);
    }

    private ShipHolderFromJson createShipHolderFromJson(String postData) {
        Gson gson = new Gson();
        final ShipHolderDTO shipHolderDTO = gson.fromJson(postData, ShipHolderDTO.class);
        log.info("Received ships to validate: " + shipHolderDTO);
        return shipsToShipHolder.convert(shipHolderDTO);
    }

    private GameBoard prepareGameBoard(String playerName, String roomName) {

        final Game game = getGameFromRoom(roomName);
        final GameBoardHolder gameBoardHolder = game.getGameBoardHolder();

        Player player = new Player(playerName);
        GameBoard playerGameBoard = gameBoardHolder.getGameBoard(player);
        playerGameBoard.reset();
        return playerGameBoard;
    }

    private Game getGameFromRoom(String roomName) {
        return roomHolder.getRoom(roomName).getGame();
    }

    private void fleetPlacementHandler(PlacingValidationResultDTO placingValidationResultDTO,
                                       GameBoard playerGameBoard) {
        if (isPlacementValid(placingValidationResultDTO)) {
            playerGameBoard.setPlacedFleet(true);
        } else {
            playerGameBoard.reset();
        }
    }

    private boolean isPlacementValid(PlacingValidationResultDTO placingValidationResultDTO) {
        return (placingValidationResultDTO.getResult()
                .equals(PlacingValidationResultDTO.Result.OK));
    }

}
