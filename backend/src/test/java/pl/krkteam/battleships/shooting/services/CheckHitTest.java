package pl.krkteam.battleships.shooting.services;

import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.shooting.dto.result.ResultHitDTO;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CheckHitTest {

    @Test
    public void testGetShotResultAndExpectHit() throws UnexpectedShotResultException {
        final Coordinates coordinates1 = new Coordinates(3, 4);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(5, 4);
        final Coordinates coordinates4 = new Coordinates(6, 4);
        List<Coordinates> coordinatesListA = Arrays.asList(coordinates1, coordinates2, coordinates3, coordinates4);

        GameBoard gameBoard = new GameBoard();
        gameBoard.createShip(coordinatesListA);

        final Board board = gameBoard.getBoard();

        Coordinates shotCoor = new Coordinates(3, 4);
        ChainOfShotResult chainOfShotResult = new CheckMiss();

        assertEquals(chainOfShotResult.getShotResult(shotCoor, board).getClass(), ResultHitDTO.class);

    }
}
