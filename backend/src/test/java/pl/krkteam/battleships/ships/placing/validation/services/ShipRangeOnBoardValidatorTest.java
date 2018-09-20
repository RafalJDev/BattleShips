package pl.krkteam.battleships.ships.placing.validation.services;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.BoardSize;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ShipRangeOnBoardValidatorTest {
    Board board;

    @BeforeTest
    void prepareBoard() {
        board = new Board(new BoardSize(10));
    }


    @Test
    void testIsVerticalShipInRange() {
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(0, 2), new CoordinatesFromJson(1, 2), new CoordinatesFromJson(2, 2)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);

        ShipRangeOnBoardValidator shipRangeOnBoardValidator = new ShipRangeOnBoardValidator();

        assertTrue(shipRangeOnBoardValidator.isShipInRange(shipFromJson, board));
    }

    @Test
    void testIsHorizontalShipInRange() {
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(0, 0), new CoordinatesFromJson(0, 1), new CoordinatesFromJson(0, 2)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);

        ShipRangeOnBoardValidator shipRangeOnBoardValidator = new ShipRangeOnBoardValidator();

        assertTrue(shipRangeOnBoardValidator.isShipInRange(shipFromJson, board));
    }


    @Test
    void testIsVerticalShipInOutOfRange() {
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(-1, 2), new CoordinatesFromJson(0, 2), new CoordinatesFromJson(1, 2)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);

        ShipRangeOnBoardValidator shipRangeOnBoardValidator = new ShipRangeOnBoardValidator();

        assertFalse(shipRangeOnBoardValidator.isShipInRange(shipFromJson, board));
    }

    @Test
    void testIsHorizontalShipInOutOfRange() {
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(0, -1), new CoordinatesFromJson(0, 0), new CoordinatesFromJson(0, 1)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);

        ShipRangeOnBoardValidator shipRangeOnBoardValidator = new ShipRangeOnBoardValidator();

        assertFalse(shipRangeOnBoardValidator.isShipInRange(shipFromJson, board));
    }


    @Test
    void testIsCoordinateInBoard() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(2, 2);
        BoardSize boardSize = board.getBoardSize();

        assertTrue(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }

    @Test
    void testIsCoordinateInBoardForBothCoorOutOfRange() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(-1, -3);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }


    @Test
    void testIsCoordinateInBoardForXCoorOutOfRange() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(0, -3);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }

    @Test
    void testIsCoordinateInBoardForYCoorOutOfRange() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(-1, 2);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }

    @Test
    void testIsCoordinateInBoardForRightBottomOutOfRange() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(12, 14);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }


    @Test
    void testIsCoordinateInBoardForRightBottomOutOfRangeY() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(10, 4);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }

    @Test
    void testIsCoordinateInBoardForRightBottomOutOfRangeX() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(3, 10);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }
}
