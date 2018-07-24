package pl.krkteam.battleships.common.domain;

import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.cell.Cell;
import pl.krkteam.battleships.common.domain.cell.Mast;
import pl.krkteam.battleships.common.domain.ship.ShipHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GameBoardTest {

    @Test
    public void testGetBoardAndExpectDimensions() {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesList = Arrays.asList(coordinates1, coordinates2,
                coordinates3, coordinates4);

        GameBoard gameBoard = new GameBoard();
        gameBoard.createShip(coordinatesList);

        Board board = gameBoard.getBoard();

        assertEquals(board.getBoardSize().getDimensions(), 10);

    }

    @Test
    public void testGetShipHolder() {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesList = Arrays.asList(coordinates1, coordinates2,
                coordinates3, coordinates4);

        GameBoard gameBoard = new GameBoard();
        gameBoard.createShip(coordinatesList);

        final ShipHolder shipHolder = gameBoard.getShipHolder();
        assertTrue(shipHolder.getShip(0).getMastList().get(2).getClass().equals(Mast.class));
    }

    @Test
    public void testCreateShip() {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesList = Arrays.asList(coordinates1, coordinates2,
                coordinates3, coordinates4);

        GameBoard gameBoard = new GameBoard();
        gameBoard.createShip(coordinatesList);

        Board board = gameBoard.getBoard();
        final Map<Coordinates, Cell> coordinatesCellMap = board.getCoordinatesCellMap();

        assertEquals(coordinatesCellMap.size(), coordinatesCellMap.size());
        assertTrue(coordinatesCellMap.containsKey(coordinates1));
        assertTrue(coordinatesCellMap.containsKey(coordinates2));
        assertTrue(coordinatesCellMap.containsKey(coordinates3));
        assertTrue(coordinatesCellMap.containsKey(coordinates4));
    }
}