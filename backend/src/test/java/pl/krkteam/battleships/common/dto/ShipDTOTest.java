package pl.krkteam.battleships.common.dto;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class ShipDTOTest {

    @Test
    public void testToString() {
        final CoordinateDTO[] coordinateDTOS = new CoordinateDTO[]{new CoordinateDTO(2, 7)};
        ShipDTO shipDTO = new ShipDTO(coordinateDTOS);

        assertEquals(shipDTO.toString(), "ShipDTO{" +
                "coordinateDTOS=" + Arrays.toString(coordinateDTOS) +
                '}');
    }

    @Test
    public void testGetCoordinates() {
        final CoordinateDTO coordinateDTOA = new CoordinateDTO(2, 7);
        final CoordinateDTO coordinateDTOB = new CoordinateDTO(2, 8);
        final CoordinateDTO[] coordinateDTOS = new CoordinateDTO[]{
                coordinateDTOA,
                coordinateDTOB};

        ShipDTO shipDTO = new ShipDTO(coordinateDTOS);

        assertEquals(shipDTO.getCoordinates(), coordinateDTOS);
    }
}