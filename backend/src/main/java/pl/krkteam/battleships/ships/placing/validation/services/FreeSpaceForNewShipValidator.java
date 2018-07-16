package pl.krkteam.battleships.ships.placing.validation.services;

import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.BoardSize;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

import java.util.ArrayList;
import java.util.List;

class FreeSpaceForNewShipValidator {
    boolean isFreeSpaceForNewShip(ShipFromJson shipFromJson, Board board, Direction direction) {
        switch (direction) {
            case ONE_MAST:
                return checkFreeSpaceVertical(shipFromJson, board);
            case VERTICAL:
                return checkFreeSpaceVertical(shipFromJson, board);
            case HORIZONTAL:
                return checkFreeSpaceHorizontal(shipFromJson, board);
            default:
                return false;
        }
    }


    private boolean checkFreeSpaceVertical(ShipFromJson shipFromJson, Board board) {
        CoordinatesFromJson[] coordinatesFromJsonArray = shipFromJson.getCoordinates();
        final int startingY = coordinatesFromJsonArray[0].getY();
        final int X = coordinatesFromJsonArray[0].getX();

        for (int i = startingY - 1; i <= startingY + coordinatesFromJsonArray.length; i++) {
            List<CoordinatesFromJson> cellSpaceList = generateSpaceForCellHorizontal(i, X, board.getBoardSize());
            if (areCellsBusy(board, cellSpaceList)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkFreeSpaceHorizontal(ShipFromJson shipFromJson, Board board) {
        CoordinatesFromJson[] coordinatesFromJsonArray = shipFromJson.getCoordinates();
        final int startingX = coordinatesFromJsonArray[0].getX();
        final int Y = coordinatesFromJsonArray[0].getY();

        for (int i = startingX - 1; i <= startingX + coordinatesFromJsonArray.length; i++) {
            List<CoordinatesFromJson> cellSpaceList = generateSpaceForCellVertical(Y, i, board.getBoardSize());
            if (areCellsBusy(board, cellSpaceList)) {
                return false;
            }
        }
        return true;
    }

    private boolean areCellsBusy(Board board, List<CoordinatesFromJson> cellSpaceList) {
        for (CoordinatesFromJson coordinatesJson : cellSpaceList) {
            if (!board.isCellEmpty(new Coordinates(coordinatesJson.getY(), coordinatesJson.getX()))) {
                return true;
            }
        }
        return false;
    }


    private List<CoordinatesFromJson> generateSpaceForCellHorizontal(int y, int x, BoardSize boardSize) {
        List<CoordinatesFromJson> spaceForCellList = new ArrayList<>(3);
        for (int i = x - 1; i <= x + 1; i++) {
            CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(y, i);
            if (ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize)) {
                spaceForCellList.add(coordinatesFromJson);
            }
        }
        return spaceForCellList;
    }


    private List<CoordinatesFromJson> generateSpaceForCellVertical(int y, int x, BoardSize boardSize) {
        List<CoordinatesFromJson> spaceForCellList = new ArrayList<>(3);
        for (int i = y - 1; i <= y + 1; i++) {
            CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(i, x);
            if (ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize)) {
                spaceForCellList.add(coordinatesFromJson);
            }
        }
        return spaceForCellList;
    }
}
