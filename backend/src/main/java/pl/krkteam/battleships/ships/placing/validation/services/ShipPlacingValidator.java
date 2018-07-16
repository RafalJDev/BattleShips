package pl.krkteam.battleships.ships.placing.validation.services;

import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

class ShipPlacingValidator {
    private Board board;

    ShipPlacingValidator(Board board) {
        this.board = board;
    }

    boolean validateSingleShip(ShipFromJson shipFromJson) {
        Direction direction = ShipDirectionChecker.whatDirection(shipFromJson);
        ShipRangeOnBoardValidator shipRangeOnBoardValidator = new ShipRangeOnBoardValidator();
        if (!shipRangeOnBoardValidator.isShipInRange(shipFromJson, board)) {
            return false;
        }
        ShipInOneLineValidator shipInOneLineValidator = new ShipInOneLineValidator();
        if (!shipInOneLineValidator.isShipInOneLine(shipFromJson, direction)) {
            return false;
        }
        FreeSpaceForNewShipValidator freeSpaceForNewShipValidator = new FreeSpaceForNewShipValidator();
        return freeSpaceForNewShipValidator.isFreeSpaceForNewShip(shipFromJson, board, direction);
    }
}
