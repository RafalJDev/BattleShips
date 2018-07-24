package pl.krkteam.battleships.shooting.services;

import pl.krkteam.battleships.common.domain.Coordinates;

public class UnexpectedShotResultException extends Exception {

    private Coordinates shotCoordinates;

    public UnexpectedShotResultException(Coordinates shotCoordinates) {
        this.shotCoordinates = shotCoordinates;
    }

    @Override
    public String getMessage() {
        return "Wrong shot on coordinates: " + "y="
                + shotCoordinates.getY() + " x=" + shotCoordinates.getX();
    }
}
