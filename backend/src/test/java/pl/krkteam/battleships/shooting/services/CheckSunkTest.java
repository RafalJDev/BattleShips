package pl.krkteam.battleships.shooting.services;

import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.shooting.dto.result.ResultSunkDTO;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CheckSunkTest {

    @Test
    void testGetShotResultAndExpectSunkOneMastShip() throws UnexpectedShotResultException {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesListA = Arrays.asList(coordinates1, coordinates2,
                coordinates3, coordinates4);

        final Coordinates coordinatesB1 = new Coordinates(8, 4);
        List<Coordinates> coordinatesListB = Arrays.asList(coordinatesB1);

        GameBoard gameBoard = new GameBoard();
        gameBoard.createShip(coordinatesListA);
        gameBoard.createShip(coordinatesListB);

        final Board board = gameBoard.getBoard();
        ChainOfShotResult chainOfShotResult = new CheckMiss();

        Coordinates shotCoor = new Coordinates(8, 4);
        chainOfShotResult.getShotResult(shotCoor, board);

        assertEquals(chainOfShotResult.getShotResult(shotCoor, board).getClass(), ResultSunkDTO.class);
    }

    @Test
    void testGetShotResultAndExpectSunkTwoMastShip() throws UnexpectedShotResultException {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesListA = Arrays.asList(coordinates1, coordinates2,
                coordinates3, coordinates4);

        final Coordinates coordinatesB1 = new Coordinates(8, 4);
        final Coordinates coordinatesB2 = new Coordinates(8, 5);
        List<Coordinates> coordinatesListB = Arrays.asList(coordinatesB1, coordinatesB2);

        GameBoard gameBoard = new GameBoard();
        gameBoard.createShip(coordinatesListA);
        gameBoard.createShip(coordinatesListB);

        final Board board = gameBoard.getBoard();
        ChainOfShotResult chainOfShotResult = new CheckMiss();

        Coordinates shotCoor = new Coordinates(8, 4);
        chainOfShotResult.getShotResult(shotCoor, board);

        shotCoor = new Coordinates(8, 5);
        chainOfShotResult.getShotResult(shotCoor, board);

        assertEquals(chainOfShotResult.getShotResult(shotCoor, board).getClass(), ResultSunkDTO.class);
    }
}
