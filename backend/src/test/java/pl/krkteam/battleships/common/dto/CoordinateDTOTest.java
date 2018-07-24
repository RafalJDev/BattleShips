package pl.krkteam.battleships.common.dto;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CoordinateDTOTest {

    @Test
    public void testToString() {
        CoordinateDTO coordinateDTO = new CoordinateDTO(3, 2);

        assertEquals(coordinateDTO.toString(), "CoordinateDTOS{" +
                "x=" + 2 +
                ", y=" + 3 +
                '}');
    }

    @Test
    public void testGetX() {
        CoordinateDTO coordinateDTO = new CoordinateDTO(3, 2);

        assertEquals(coordinateDTO.getX(), 2);
    }

    @Test
    public void testGetY() {
        CoordinateDTO coordinateDTO = new CoordinateDTO(3, 2);

        assertEquals(coordinateDTO.getY(), 3);
    }
}