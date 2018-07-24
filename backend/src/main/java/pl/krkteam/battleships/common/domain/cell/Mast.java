package pl.krkteam.battleships.common.domain.cell;

import pl.krkteam.battleships.common.domain.ship.Ship;

public class Mast implements Cell {

    private MastState mastState = MastState.MAST_PRESENT;

    private Ship ship;

    public Mast(Ship ship) {
        this.ship = ship;
        ship.attachMast(this);
    }

    public void changeMastState(MastState mastState) {
        this.mastState = mastState;
        ship.notifyMastChangeState();
    }

    public MastState getMastState() {
        return mastState;
    }

    public Ship getShip() {
        return ship;
    }
}
