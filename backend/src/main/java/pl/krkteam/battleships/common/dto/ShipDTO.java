package pl.krkteam.battleships.common.dto;

import java.util.Arrays;

public class ShipDTO {

    private CoordinateDTO[] coordinates;

    public ShipDTO(CoordinateDTO[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "ShipDTO{" +
                "coordinateDTOS=" + Arrays.toString(coordinates) +
                '}';
    }

    public CoordinateDTO[] getCoordinates() {
        return coordinates;
    }
}
