package pl.krkteam.battleships.ships.placing.validation.services;

import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.BoardSize;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

class ShipRangeOnBoardValidator {
    boolean isShipInRange(ShipFromJson shipFromJson, Board board) {
        BoardSize boardSize = board.getBoardSize();
        CoordinatesFromJson[] coordinatesFromJsonArray = shipFromJson.getCoordinates();
        for (CoordinatesFromJson coordinatesFromJson : coordinatesFromJsonArray) {
            if (!isCoordinateInBoard(coordinatesFromJson, boardSize)) {
                return false;
            }
        }
        return true;
    }

    static boolean isCoordinateInBoard(CoordinatesFromJson coordinatesFromJson, BoardSize boardSize) {
        int boardRange = boardSize.getDimensions() - 1;
        int coorX = coordinatesFromJson.getX();
        int coorY = coordinatesFromJson.getY();
        return checkIsCoorInRange(boardRange, coorX, coorY);
    }

    private static boolean checkIsCoorInRange(int boardRange, int coorX, int coorY) {
        return (coorX >= 0 && coorX <= boardRange &&
                coorY >= 0 && coorY <= boardRange);
    }

}
