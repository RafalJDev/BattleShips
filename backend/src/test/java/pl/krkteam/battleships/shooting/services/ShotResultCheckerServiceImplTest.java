package pl.krkteam.battleships.shooting.services;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.dto.CoordinateDTO;
import pl.krkteam.battleships.shooting.conventers.CoordinateDTOtoCoordinates;
import pl.krkteam.battleships.shooting.dto.ShotDTO;
import pl.krkteam.battleships.shooting.dto.result.ResultHitDTO;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ShotResultCheckerServiceImplTest {

    ShotResultCheckerService shotResultCheckerService;

    CoordinateDTOtoCoordinates coordinateDTOtoCoordinates;

    @BeforeMethod
    public void setUp() {
        coordinateDTOtoCoordinates = new CoordinateDTOtoCoordinates();
        shotResultCheckerService = new ShotResultCheckerServiceImpl(coordinateDTOtoCoordinates);
    }

    @Test
    public void testCheckShotResultAndExpectHit() {
        final Coordinates coordinates1 = new Coordinates(4, 3);
        final Coordinates coordinates2 = new Coordinates(4, 4);
        final Coordinates coordinates3 = new Coordinates(4, 5);
        final Coordinates coordinates4 = new Coordinates(4, 6);
        List<Coordinates> coordinatesListA = Arrays.asList(coordinates1,
                coordinates2, coordinates3, coordinates4);

        GameBoard gameBoard = new GameBoard();
        gameBoard.createShip(coordinatesListA);

        ShotDTO shotDTO = new ShotDTO(new CoordinateDTO(4, 3));
        assertEquals(shotResultCheckerService.checkShotResult(shotDTO, gameBoard).getClass(), ResultHitDTO.class);
    }
}
