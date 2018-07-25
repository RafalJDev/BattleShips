package pl.krkteam.battleships.common.domain.ship;

import pl.krkteam.battleships.common.domain.cell.Mast;
import pl.krkteam.battleships.common.domain.cell.MastState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Ship {

    private List<Mast> mastList = new ArrayList<>();
    private ShipState shipState = ShipState.FLOATING;
    private ShipHolder shipHolder;

    public Ship(ShipHolder shipHolder) {
        this.shipHolder = shipHolder;
        shipHolder.attachShip(this);
    }

    public void attachMast(final Mast mast) {
        mastList.add(mast);
    }

    public List<Mast> getMastList() {
        return Collections.unmodifiableList(mastList);
    }

    public void notifyMastChangeState() {
        final Optional<Mast> anyPresentMast =
                mastList
                        .stream()
                        .filter(mast -> mast.getMastState()
                                .equals(MastState.MAST_PRESENT))
                        .findAny();
        if (!anyPresentMast.isPresent()) {
            shipState = ShipState.DESTROYED;
            shipHolder.notifyShipChangeState();
        }
    }

    public ShipState getShipState() {
        return shipState;
    }

    public ShipHolder getShipHolder() {
        return shipHolder;
    }
}
