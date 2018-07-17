package pl.krkteam.battleships.ships.placing.validation.services;

import org.springframework.stereotype.Service;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.dto.PlacingValidationResultDTO;
import pl.krkteam.battleships.ships.placing.validation.converters.ShipJsonToCoordinates;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipHolderFromJson;

import java.util.List;

@Service
public class ShipsPlacingValidatorServiceImpl implements ShipsPlacingValidatorService {

    private final ShipJsonToCoordinates shipJsonToCoordinates;

    public ShipsPlacingValidatorServiceImpl(ShipJsonToCoordinates shipJsonToCoordinates) {
        this.shipJsonToCoordinates = shipJsonToCoordinates;
    }

    @Override
    public PlacingValidationResultDTO validateShipLocation(ShipHolderFromJson shipHolderFromJson, GameBoard gameBoard) {
        final List<ShipFromJson> shipFromJsonList = shipHolderFromJson.getShipFromJsonList();
        Board board = gameBoard.getBoard();

        ShipPlacingValidator shipPlacingValidator = new ShipPlacingValidator(board);
        for (ShipFromJson shipFromJson : shipFromJsonList) {
            if (!shipPlacingValidator.validateSingleShip(shipFromJson)) {
                return new PlacingValidationResultDTO(PlacingValidationResultDTO.Result.WRONG);
            }
            List<Coordinates> shipCoordinatesList = shipJsonToCoordinates.convert(shipFromJson);
            gameBoard.createShip(shipCoordinatesList);
        }
        return new PlacingValidationResultDTO(PlacingValidationResultDTO.Result.OK);
    }

}
