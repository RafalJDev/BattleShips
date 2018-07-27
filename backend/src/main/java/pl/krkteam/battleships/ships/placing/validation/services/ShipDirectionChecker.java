package pl.krkteam.battleships.ships.placing.validation.services;

import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

class ShipDirectionChecker {
    static Direction whatDirection(ShipFromJson shipFromJson) {
        CoordinatesFromJson[] coordinatesFromJsonArray = shipFromJson.getCoordinates();
        if (isOneMastShip(coordinatesFromJsonArray)) {
            return Direction.ONE_MAST;
        }
        if (checkIfShipHorizontal(coordinatesFromJsonArray)) {
            return Direction.HORIZONTAL;
        }
        if (checkIfShipVertical(coordinatesFromJsonArray)) {
            return Direction.VERTICAL;
        }
        return Direction.WRONG;
    }

    private static boolean isOneMastShip(CoordinatesFromJson[] coordinatesFromJsonArray) {
        return coordinatesFromJsonArray.length == 1;
    }

    private static boolean checkIfShipHorizontal(CoordinatesFromJson[] coordinatesFromJsonArray) {
        return (coordinatesFromJsonArray[0].getX() + 1 == coordinatesFromJsonArray[1].getX() &&
                coordinatesFromJsonArray[0].getY() == coordinatesFromJsonArray[1].getY());
    }

    private static boolean checkIfShipVertical(CoordinatesFromJson[] coordinatesFromJsonArray) {
        return (coordinatesFromJsonArray[0].getY() + 1 == coordinatesFromJsonArray[1].getY() &&
                coordinatesFromJsonArray[0].getX() == coordinatesFromJsonArray[1].getX());
    }
}
