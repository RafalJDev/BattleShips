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
    private Board board;

    @BeforeTest
    private void prepareBoard() {
        board = new Board(new BoardSize(10));
    }


    @Test
    public void testIsVerticalShipInRange() {
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(0, 2), new CoordinatesFromJson(1, 2), new CoordinatesFromJson(2, 2)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);

        ShipRangeOnBoardValidator shipRangeOnBoardValidator = new ShipRangeOnBoardValidator();

        assertTrue(shipRangeOnBoardValidator.isShipInRange(shipFromJson, board));
    }

    @Test
    public void testIsHorizontalShipInRange() {
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(0, 0), new CoordinatesFromJson(0, 1), new CoordinatesFromJson(0, 2)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);

        ShipRangeOnBoardValidator shipRangeOnBoardValidator = new ShipRangeOnBoardValidator();

        assertTrue(shipRangeOnBoardValidator.isShipInRange(shipFromJson, board));
    }


    @Test
    public void testIsVerticalShipInOutOfRange() {
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(-1, 2), new CoordinatesFromJson(0, 2), new CoordinatesFromJson(1, 2)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);

        ShipRangeOnBoardValidator shipRangeOnBoardValidator = new ShipRangeOnBoardValidator();

        assertFalse(shipRangeOnBoardValidator.isShipInRange(shipFromJson, board));
    }

    @Test
    public void testIsHorizontalShipInOutOfRange() {
        CoordinatesFromJson[] coordinatesFromJsons = new CoordinatesFromJson[]
                {new CoordinatesFromJson(0, -1), new CoordinatesFromJson(0, 0), new CoordinatesFromJson(0, 1)};
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsons);

        ShipRangeOnBoardValidator shipRangeOnBoardValidator = new ShipRangeOnBoardValidator();

        assertFalse(shipRangeOnBoardValidator.isShipInRange(shipFromJson, board));
    }


    @Test
    public void testIsCoordinateInBoard() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(2, 2);
        BoardSize boardSize = board.getBoardSize();

        assertTrue(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }

    @Test
    public void testIsCoordinateInBoardForBothCoorOutOfRange() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(-1, -3);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }


    @Test
    public void testIsCoordinateInBoardForXCoorOutOfRange() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(0, -3);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }

    @Test
    public void testIsCoordinateInBoardForYCoorOutOfRange() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(-1, 2);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }

    @Test
    public void testIsCoordinateInBoardForRightBottomOutOfRange() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(12, 14);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }


    @Test
    public void testIsCoordinateInBoardForRightBottomOutOfRangeY() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(10, 4);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }

    @Test
    public void testIsCoordinateInBoardForRightBottomOutOfRangeX() {
        CoordinatesFromJson coordinatesFromJson = new CoordinatesFromJson(3, 10);
        BoardSize boardSize = board.getBoardSize();

        assertFalse(ShipRangeOnBoardValidator.isCoordinateInBoard(coordinatesFromJson, boardSize));
    }
}
