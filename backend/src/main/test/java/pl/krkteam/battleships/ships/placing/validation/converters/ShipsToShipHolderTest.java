package pl.krkteam.battleships.ships.placing.validation.converters;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShipsToShipHolderTest {
    private ShipsToShipHolder converter;

    @BeforeMethod
    public void setUp() {
        converter = new ShipsToShipHolder();
    }

    @Test
    public void testConvert() {
    }
}