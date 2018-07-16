package pl.krkteam.battleships.common.domain.ship;

import java.util.ArrayList;
import java.util.List;

public class ShipHolder {
    private List<Ship> shipList = new ArrayList<>();

    public void addShip(final Ship ship) {
        shipList.add(ship);
    }

    public Ship getShip(int index) {
        return shipList.get(index);
    }
}
