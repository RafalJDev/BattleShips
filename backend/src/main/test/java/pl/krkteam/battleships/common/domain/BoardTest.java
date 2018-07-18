package pl.krkteam.battleships.common.domain;

import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.cell.mastState.PresentMast;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BoardTest {

    @Test
    public void testPutCoordinatesAndCell() {
    }

    @Test
    public void testGetBoardSize() {
    }

    @Test
    public void testGetCoordinatesCellMap() {
    }

    @Test
    public void testIsCellEmptyAndExpectIsEmpty() {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesList = Arrays.asList(coordinates1, coordinates2,
                coordinates3, coordinates4);


        Board board = new Board(new BoardSize(10));
        coordinatesList.forEach(coor -> {
            board.putCoordinatesAndCell(coor, new PresentMast());
        });

        assertTrue(board.isCellEmpty(new Coordinates(4, 7)));
    }

    @Test
    public void testIsCellEmptyAndExpectNotEmpty() {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesList = Arrays.asList(coordinates1, coordinates2,
                coordinates3, coordinates4);


        Board board = new Board(new BoardSize(10));
        coordinatesList.forEach(coor -> {
            board.putCoordinatesAndCell(coor, new PresentMast());
        });

        assertFalse(board.isCellEmpty(new Coordinates(5, 4)));
    }
}