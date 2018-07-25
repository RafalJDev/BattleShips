package pl.krkteam.battleships.common.dto;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PlacingValidationResultDTOTest {

    @Test
    public void testGetResult() {
        PlacingValidationResultDTO placingValidationResultDTO = new PlacingValidationResultDTO(
                PlacingValidationResultDTO.Result.OK);

        assertEquals(placingValidationResultDTO.getResult(), PlacingValidationResultDTO.Result.OK);

    }
}
