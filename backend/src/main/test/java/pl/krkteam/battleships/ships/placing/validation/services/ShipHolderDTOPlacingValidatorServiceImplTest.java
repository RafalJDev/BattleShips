package pl.krkteam.battleships.ships.placing.validation.services;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.dto.PlacingValidationResultDTO;
import pl.krkteam.battleships.ships.placing.validation.converters.ShipJsonToCoordinates;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipHolderFromJson;

import static org.testng.Assert.assertEquals;

public class ShipHolderDTOPlacingValidatorServiceImplTest {

    private ShipsPlacingValidatorServiceImpl shipPlacingValidator;

    private GameBoard gameBoard;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ShipJsonToCoordinates shipJsonToCoordinates = new ShipJsonToCoordinates();
        shipPlacingValidator = new ShipsPlacingValidatorServiceImpl(shipJsonToCoordinates);
        gameBoard = new GameBoard();
    }

    @Test
    public void testValidateShipLocationAndExpectValid() {
        ShipHolderFromJson shipHolderFromJson = new ShipHolderFromJson();

        ShipFromJson shipFromJsonA = new ShipFromJson(new CoordinatesFromJson[]{
                new CoordinatesFromJson(0, 2),
                new CoordinatesFromJson(1, 2),
                new CoordinatesFromJson(2, 2),
                new CoordinatesFromJson(3, 2)
        });
        ShipFromJson shipFromJsonB = new ShipFromJson(new CoordinatesFromJson[]{
                new CoordinatesFromJson(6, 1),
                new CoordinatesFromJson(6, 2),
                new CoordinatesFromJson(6, 3)
        });

        shipHolderFromJson.addShipFromJson(shipFromJsonA);
        shipHolderFromJson.addShipFromJson(shipFromJsonB);

        assertEquals(shipPlacingValidator.validateShipLocation(shipHolderFromJson, gameBoard).getResult(),
                PlacingValidationResultDTO.Result.OK);
    }

    @Test
    public void testValidateShipLocationAndExpectNotValid() {
        ShipHolderFromJson shipHolderFromJson = new ShipHolderFromJson();

        ShipFromJson shipFromJsonA = new ShipFromJson(new CoordinatesFromJson[]{
                new CoordinatesFromJson(0, 2),
                new CoordinatesFromJson(1, 2),
                new CoordinatesFromJson(2, 2),
                new CoordinatesFromJson(3, 2)
        });
        ShipFromJson shipFromJsonB = new ShipFromJson(new CoordinatesFromJson[]{
                new CoordinatesFromJson(2, 0),
                new CoordinatesFromJson(2, 1),
                new CoordinatesFromJson(2, 2)
        });

        shipHolderFromJson.addShipFromJson(shipFromJsonA);
        shipHolderFromJson.addShipFromJson(shipFromJsonB);

        assertEquals(shipPlacingValidator.validateShipLocation(shipHolderFromJson, gameBoard).getResult(),
                PlacingValidationResultDTO.Result.WRONG);
    }
}