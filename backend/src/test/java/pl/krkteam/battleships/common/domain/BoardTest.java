package pl.krkteam.battleships.common.domain;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BoardTest {

    //todo not written test
//    @Test
//    void testPutCoordinatesAndCell() {
//    }
//
//    @Test
//    void testGetBoardSize() {
//    }
//
//    @Test
//    void testGetCoordinatesCellMap() {
//    }

    @Test
    void testIsCellEmptyAndExpectIsEmpty() {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesList = Arrays.asList(coordinates1, coordinates2,
                coordinates3, coordinates4);


        GameBoard gameBoard = new GameBoard();
        Board board = gameBoard.getBoard();
        gameBoard.createShip(coordinatesList);

        assertTrue(board.isCellEmpty(new Coordinates(4, 7)));
    }

    @Test
    void testIsCellEmptyAndExpectNotEmpty() {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesList = Arrays.asList(coordinates1, coordinates2,
                coordinates3, coordinates4);


        GameBoard gameBoard = new GameBoard();
        Board board = gameBoard.getBoard();
        gameBoard.createShip(coordinatesList);

        assertFalse(board.isCellEmpty(new Coordinates(5, 4)));
    }
}