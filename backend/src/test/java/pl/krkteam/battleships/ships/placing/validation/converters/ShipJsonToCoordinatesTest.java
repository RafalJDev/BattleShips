package pl.krkteam.battleships.ships.placing.validation.converters;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

import java.util.List;

import static org.testng.Assert.*;

public class ShipJsonToCoordinatesTest {
    ShipJsonToCoordinates converter;

    @BeforeMethod
    void setUp() {
        converter = new ShipJsonToCoordinates();
    }

    @Test
    void testConvert() {
        CoordinatesFromJson coordinatesFromJsonA = new CoordinatesFromJson(5, 4);
        CoordinatesFromJson coordinatesFromJsonB = new CoordinatesFromJson(6, 4);
        CoordinatesFromJson coordinatesFromJsonC = new CoordinatesFromJson(7, 4);
        CoordinatesFromJson[] coordinatesFromJsonArray = new CoordinatesFromJson[]{
                coordinatesFromJsonA,
                coordinatesFromJsonB,
                coordinatesFromJsonC
        };
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsonArray);

        final List<Coordinates> coordinates = converter.convert(shipFromJson);

        assertEquals(coordinates.get(0).getX(), 4);
        assertEquals(coordinates.get(2).getY(), 7);
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        CoordinatesFromJson coordinatesFromJsonA = new CoordinatesFromJson(5, 4);
        CoordinatesFromJson coordinatesFromJsonB = new CoordinatesFromJson(6, 4);
        CoordinatesFromJson coordinatesFromJsonC = new CoordinatesFromJson(7, 4);
        CoordinatesFromJson[] coordinatesFromJsonArray = new CoordinatesFromJson[]{
                coordinatesFromJsonA,
                coordinatesFromJsonB,
                coordinatesFromJsonC
        };
        ShipFromJson shipFromJson = new ShipFromJson(coordinatesFromJsonArray);

        assertNotNull(converter.convert(shipFromJson));
    }
}