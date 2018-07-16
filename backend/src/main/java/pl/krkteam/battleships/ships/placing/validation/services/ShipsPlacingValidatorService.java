package pl.krkteam.battleships.ships.placing.validation.services;

import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.dto.PlacingValidationResultDTO;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipHolderFromJson;

public interface ShipsPlacingValidatorService {
    PlacingValidationResultDTO validateShipLocation(ShipHolderFromJson shipFromFronted, GameBoard gameBoard);
}
