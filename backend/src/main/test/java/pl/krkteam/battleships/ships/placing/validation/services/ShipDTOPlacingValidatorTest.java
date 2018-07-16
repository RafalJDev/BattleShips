package pl.krkteam.battleships.ships.placing.validation.services;

import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.ships.placing.validation.converters.ShipJsonToCoordinates;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ShipDTOPlacingValidatorTest {

    @Test
    public void testValidateSingleShipAndExpectIsValid() {
        ShipJsonToCoordinates shipJsonToCoordinates = new ShipJsonToCoordinates();

        CoordinatesFromJson[] coordinatesFromJsonsA = new CoordinatesFromJson[]
                {new CoordinatesFromJson(7, 0),
                        new CoordinatesFromJson(7, 1),
                        new CoordinatesFromJson(7, 2)};
        ShipFromJson shipFromJsonA = new ShipFromJson(coordinatesFromJsonsA);

        CoordinatesFromJson[] coordinatesFromJsonsB = new CoordinatesFromJson[]
                {new CoordinatesFromJson(2, 2),
                        new CoordinatesFromJson(3, 2),
                        new CoordinatesFromJson(4, 2)};

        ShipFromJson shipFromJsonB = new ShipFromJson(coordinatesFromJsonsB);


        final GameBoard gameBoard = new GameBoard();
        final Board board = gameBoard.getBoard();
        ShipPlacingValidator shipPlacingValidator = new ShipPlacingValidator(board);

        assertTrue(shipPlacingValidator.validateSingleShip(shipFromJsonA));

        List<Coordinates> shipCoordinatesListA = shipJsonToCoordinates.convert(shipFromJsonA);
        gameBoard.createShip(shipCoordinatesListA);

        assertTrue(shipPlacingValidator.validateSingleShip(shipFromJsonB));
    }


    @Test
    public void testValidateSingleShipAndExpectIsNotValid() {
        ShipJsonToCoordinates shipJsonToCoordinates = new ShipJsonToCoordinates();

        CoordinatesFromJson[] coordinatesFromJsonsA = new CoordinatesFromJson[]
                {new CoordinatesFromJson(7, 0),
                        new CoordinatesFromJson(7, 1),
                        new CoordinatesFromJson(7, 2)};
        ShipFromJson shipFromJsonA = new ShipFromJson(coordinatesFromJsonsA);

        CoordinatesFromJson[] coordinatesFromJsonsB = new CoordinatesFromJson[]
                {new CoordinatesFromJson(5, 2),
                        new CoordinatesFromJson(6, 2),
                        new CoordinatesFromJson(7, 2)};
        ShipFromJson shipFromJsonB = new ShipFromJson(coordinatesFromJsonsB);

        final GameBoard gameBoard = new GameBoard();
        final Board board = gameBoard.getBoard();
        ShipPlacingValidator shipPlacingValidator = new ShipPlacingValidator(board);

        assertTrue(shipPlacingValidator.validateSingleShip(shipFromJsonA));

        List<Coordinates> shipCoordinatesListA = shipJsonToCoordinates.convert(shipFromJsonA);
        gameBoard.createShip(shipCoordinatesListA);

        assertFalse(shipPlacingValidator.validateSingleShip(shipFromJsonB));
    }
}