package pl.krkteam.battleships.common.dto;

import java.util.Arrays;

public class ShipFromFronted {

    public String[] ships;

    @Override
    public String toString() {
        return "ShipFromFronted{" +
                "shipDTOS=" + Arrays.toString(ships) +
                '}';
    }
}
