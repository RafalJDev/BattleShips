package pl.krkteam.battleships.shooting.services;

import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.shooting.dto.result.ResultHitDTO;
import pl.krkteam.battleships.shooting.dto.result.ResultPlayerWonDTO;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CheckPlayerWonTest {

    @Test
    public void testGetShotResultAndExpectPlayerWon() throws UnexpectedShotResultException {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesList = Arrays.asList(coordinates1, coordinates2,
                coordinates3, coordinates4);

        GameBoard gameBoard = new GameBoard();
        gameBoard.createShip(coordinatesList);

        final Board board = gameBoard.getBoard();
        ChainOfShotResult chainOfShotResult = new CheckMiss();
        Coordinates shotCoor = new Coordinates(3, 4);
        chainOfShotResult.getShotResult(shotCoor, board);
        assertEquals(chainOfShotResult.getShotResult(shotCoor, board).getClass(), ResultHitDTO.class);

        shotCoor = new Coordinates(4, 4);
        chainOfShotResult.getShotResult(shotCoor, board);
        assertEquals(chainOfShotResult.getShotResult(shotCoor, board).getClass(), ResultHitDTO.class);

        shotCoor = new Coordinates(5, 4);
        chainOfShotResult.getShotResult(shotCoor, board);
        assertEquals(chainOfShotResult.getShotResult(shotCoor, board).getClass(), ResultHitDTO.class);

        shotCoor = new Coordinates(6, 4);
        assertEquals(chainOfShotResult.getShotResult(shotCoor, board).getClass(), ResultPlayerWonDTO.class);
    }
}
