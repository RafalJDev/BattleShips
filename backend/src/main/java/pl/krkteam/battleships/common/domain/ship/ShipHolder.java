package pl.krkteam.battleships.common.domain.ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShipHolder {
    private List<Ship> shipList = new ArrayList<>();
    private FleetState fleetState = FleetState.FLEET_FLOATING;

    public void attachShip(final Ship ship) {
        shipList.add(ship);
    }

    public Ship getShip(int index) {
        return shipList.get(index);
    }

    public void notifyShipChangeState() {
        final Optional<Ship> anyFloatingShip = shipList.stream().filter(
                mast -> mast.getShipState().equals(ShipState.FLOATING)).findAny();
        if (!anyFloatingShip.isPresent()) {
            fleetState = FleetState.FLEET_SUNK;
        }
    }

    public FleetState getFleetState() {
        return fleetState;
    }
}
