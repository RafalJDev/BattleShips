package pl.krkteam.battleships.ships.placing.validation.services;

import org.testng.annotations.Test;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ShipInOneLineValidatorTest {

    @Test
    public void testIsShipInOneLineVertical() {
        CoordinatesFromJson[] coordinatesFromJsons =new CoordinatesFromJson[]
                {new CoordinatesFromJson(0,2),new CoordinatesFromJson(1,2),new CoordinatesFromJson(2,2)};
        ShipFromJson shipFromJson =new ShipFromJson(coordinatesFromJsons);
        Direction direction=Direction.VERTICAL;

        ShipInOneLineValidator shipInOneLineValidator=new ShipInOneLineValidator();

        assertTrue(shipInOneLineValidator.isShipInOneLine(shipFromJson,direction));

    }

    @Test
    public void testIsShipInOneLineHorizontal() {
        CoordinatesFromJson[] coordinatesFromJsons =new CoordinatesFromJson[]
                {new CoordinatesFromJson(7,0),new CoordinatesFromJson(7,1),new CoordinatesFromJson(7,2)};
        ShipFromJson shipFromJson =new ShipFromJson(coordinatesFromJsons);
        Direction direction=Direction.HORIZONTAL;

        ShipInOneLineValidator shipInOneLineValidator=new ShipInOneLineValidator();

        assertTrue(shipInOneLineValidator.isShipInOneLine(shipFromJson,direction));
    }


    @Test
    public void testIsShipIneLineForOneMastShip(){
        CoordinatesFromJson[] coordinatesFromJsons=new CoordinatesFromJson[]{new CoordinatesFromJson(2,3)};
        ShipFromJson shipFromJson=new ShipFromJson(coordinatesFromJsons);
        Direction direction=Direction.ONE_MAST;

        ShipInOneLineValidator shipInOneLineValidator=new ShipInOneLineValidator();

        assertTrue(shipInOneLineValidator.isShipInOneLine(shipFromJson,direction));
    }

    @Test
    public void testIsShipInOneLineVerticalForBadShip() {
        CoordinatesFromJson[] coordinatesFromJsons =new CoordinatesFromJson[]
                {new CoordinatesFromJson(0,2),new CoordinatesFromJson(1,2),new CoordinatesFromJson(2,3)};
        ShipFromJson shipFromJson =new ShipFromJson(coordinatesFromJsons);
        Direction direction=Direction.VERTICAL;

        ShipInOneLineValidator shipInOneLineValidator=new ShipInOneLineValidator();
        assertFalse(shipInOneLineValidator.isShipInOneLine(shipFromJson,direction));
    }

    @Test
    public void testIsShipInOneLineHorizontalForBadShip() {
        CoordinatesFromJson[] coordinatesFromJsons =new CoordinatesFromJson[]
                {new CoordinatesFromJson(7,0),new CoordinatesFromJson(7,-1),new CoordinatesFromJson(7,2)};
        ShipFromJson shipFromJson =new ShipFromJson(coordinatesFromJsons);
        Direction direction=Direction.HORIZONTAL;

        ShipInOneLineValidator shipInOneLineValidator=new ShipInOneLineValidator();
        assertFalse(shipInOneLineValidator.isShipInOneLine(shipFromJson,direction));
    }
}