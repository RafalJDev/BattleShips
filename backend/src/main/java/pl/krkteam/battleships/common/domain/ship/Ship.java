package pl.krkteam.battleships.common.domain.ship;

import pl.krkteam.battleships.common.domain.cell.Mast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ship {

    private List<Mast> mastList = new ArrayList<>();
    private ShipState shipState = ShipState.FLOATING;


    public void addMast(final Mast mast) {
        mastList.add(mast);
    }

    public List<Mast> getMastList() {
        return Collections.unmodifiableList(mastList);
    }
}
