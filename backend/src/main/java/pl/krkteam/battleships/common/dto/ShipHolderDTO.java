package pl.krkteam.battleships.common.dto;

import java.util.Arrays;

public class ShipHolderDTO {

    private ShipDTO[] shipArray;

    public ShipDTO[] getShipArray() {
        return shipArray;
    }

    public ShipHolderDTO(ShipDTO[] shipArray) {
        this.shipArray = shipArray;
    }

    @Override
    public String toString() {
        return "ShipHolderDTO{" +
                "shipDTOS=" + Arrays.toString(shipArray) +
                '}';
    }
}



