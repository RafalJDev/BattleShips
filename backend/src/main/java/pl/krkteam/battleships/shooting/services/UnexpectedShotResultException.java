package pl.krkteam.battleships.shooting.services;

import pl.krkteam.battleships.common.domain.Coordinates;

public class UnexpectedShotResultException extends Exception {

    private Coordinates shotCoor;

    public UnexpectedShotResultException(Coordinates shotCoor) {
        this.shotCoor = shotCoor;
    }

    @Override
    public String getMessage() {
        return "Wrong shot on coordinates: " + "y="
                + shotCoor.getY() + " x=" + shotCoor.getX();
    }
}
