package pl.krkteam.battleships.common.dto;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CoordinateDTOTest {

    CoordinateDTO coordinateDTO = new CoordinateDTO(3, 2);

    @Test
    public void testToString() {
        assertEquals(coordinateDTO.toString(), "CoordinateDTOS{" +
                "x=" + 2 +
                ", y=" + 3 +
                '}');
    }

    @Test
    public void testGetX() {
        assertEquals(coordinateDTO.getX(), 2);
    }

    @Test
    public void testGetY() {
        assertEquals(coordinateDTO.getY(), 3);
    }
}