package pl.krkteam.battleships.ships.placing.validation.services;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FreeSpaceForNewShipDTOValidatorTest {

    @DataProvider(name = "provideBoard")
    public Object[] boardProvider() {
        GameBoard gameBoard = new GameBoard();
        List<Coordinates> coordinatesList1 = Arrays.asList(
                new Coordinates(7, 5),
                new Coordinates(8, 5),
                new Coordinates(9, 5));
        List<Coordinates> coordinatesList2 = Arrays.asList(
                new Coordinates(3, 2),
                new Coordinates(3, 3),
                new Coordinates(3, 4),
                new Coordinates(3, 5));
        gameBoard.createShip(coordinatesList1);
        gameBoard.createShip(coordinatesList2);
        return new Object[]{gameBoard};
    }

    @Test(dataProvider = "provideBoard")
    public void testIsFreeSpaceForNewHorizontalShipAndExpectFreeSpace(GameBoard gameBoard) {
        FreeSpaceForNewShipValidator freeSpaceForNewShipValidator = new FreeSpaceForNewShipValidator();
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(5, 0), new CoordinatesFromJson(5, 1), new CoordinatesFromJson(5, 2)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);
        Direction direction = ShipDirectionChecker.whatDirection(shipFromJson);
        Board board = gameBoard.getBoard();

        assertTrue(freeSpaceForNewShipValidator.isFreeSpaceForNewShip(shipFromJson, board, direction));
    }

    @Test(dataProvider = "provideBoard")
    public void testIsFreeSpaceForNewVerticalShipAndExpectFreeSpace(GameBoard gameBoard) {
        FreeSpaceForNewShipValidator freeSpaceForNewShipValidator = new FreeSpaceForNewShipValidator();
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(6, 1), new CoordinatesFromJson(7, 1), new CoordinatesFromJson(8, 1)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);
        Direction direction = ShipDirectionChecker.whatDirection(shipFromJson);
        Board board = gameBoard.getBoard();

        assertTrue(freeSpaceForNewShipValidator.isFreeSpaceForNewShip(shipFromJson, board, direction));
    }

    @Test(dataProvider = "provideBoard")
    public void testIsFreeSpaceForNewShipAndExpectNewShipInBuffer(GameBoard gameBoard) {
        FreeSpaceForNewShipValidator freeSpaceForNewShipValidator = new FreeSpaceForNewShipValidator();
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(6, 4), new CoordinatesFromJson(7, 4),
                        new CoordinatesFromJson(8, 4), new CoordinatesFromJson(9, 4)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);
        Direction direction = ShipDirectionChecker.whatDirection(shipFromJson);
        Board board = gameBoard.getBoard();

        assertFalse(freeSpaceForNewShipValidator.isFreeSpaceForNewShip(shipFromJson, board, direction));
    }


    @Test(dataProvider = "provideBoard")
    public void testIsFreeSpaceForNewShipAndExpectNewShipInImposeOnOtherShip(GameBoard gameBoard) {
        FreeSpaceForNewShipValidator freeSpaceForNewShipValidator = new FreeSpaceForNewShipValidator();
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(2, 2), new CoordinatesFromJson(3, 2),
                        new CoordinatesFromJson(4, 2), new CoordinatesFromJson(5, 2)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);
        Direction direction = ShipDirectionChecker.whatDirection(shipFromJson);
        Board board = gameBoard.getBoard();

        assertFalse(freeSpaceForNewShipValidator.isFreeSpaceForNewShip(shipFromJson, board, direction));
    }


    @Test(dataProvider = "provideBoard")
    public void testIsFreeSpaceForNewShipAndExpectFreeSpaceInLeftBottomCorner(GameBoard gameBoard) {
        FreeSpaceForNewShipValidator freeSpaceForNewShipValidator = new FreeSpaceForNewShipValidator();
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(9, 1), new CoordinatesFromJson(9, 2),
                        new CoordinatesFromJson(9, 3)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);
        Direction direction = ShipDirectionChecker.whatDirection(shipFromJson);
        Board board = gameBoard.getBoard();

        assertTrue(freeSpaceForNewShipValidator.isFreeSpaceForNewShip(shipFromJson, board, direction));
    }


    @Test(dataProvider = "provideBoard")
    public void testIsFreeSpaceForNewShipAndExpectFreeSpaceInRightTopCorner(GameBoard gameBoard) {
        FreeSpaceForNewShipValidator freeSpaceForNewShipValidator = new FreeSpaceForNewShipValidator();
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(0, 9), new CoordinatesFromJson(1, 9),
                        new CoordinatesFromJson(2, 9), new CoordinatesFromJson(3, 9)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);
        Direction direction = ShipDirectionChecker.whatDirection(shipFromJson);
        Board board = gameBoard.getBoard();

        assertTrue(freeSpaceForNewShipValidator.isFreeSpaceForNewShip(shipFromJson, board, direction));
    }

    @Test(dataProvider = "provideBoard")
    public void testIsFreeSpaceForNewOneMastShipAndExpectFreeSpace(GameBoard gameBoard) {
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]{new CoordinatesFromJson(0, 9)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);
        Direction direction = ShipDirectionChecker.whatDirection(shipFromJson);
        Board board = gameBoard.getBoard();

        FreeSpaceForNewShipValidator freeSpaceForNewShipValidator = new FreeSpaceForNewShipValidator();

        assertTrue(freeSpaceForNewShipValidator.isFreeSpaceForNewShip(shipFromJson, board, direction));
    }


    @Test(dataProvider = "provideBoard")
    public void testIsFreeSpaceForNewOneMastShipAndExpectNewShipInBuffer(GameBoard gameBoard) {
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]{new CoordinatesFromJson(7, 4)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);
        Direction direction = ShipDirectionChecker.whatDirection(shipFromJson);
        Board board = gameBoard.getBoard();

        FreeSpaceForNewShipValidator freeSpaceForNewShipValidator = new FreeSpaceForNewShipValidator();

        assertFalse(freeSpaceForNewShipValidator.isFreeSpaceForNewShip(shipFromJson, board, direction));
    }
}