package pl.krkteam.battleships.ships.placing.validation.services;

import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

class ShipDirectionChecker {
    static Direction whatDirection(ShipFromJson shipFromJson) {
        CoordinatesFromJson[] coordinatesFromJsonArray = shipFromJson.getCoordinates();
        if (coordinatesFromJsonArray.length == 1) {
            return Direction.ONE_MAST;
        }
        if (coordinatesFromJsonArray[0].getX() + 1 == coordinatesFromJsonArray[1].getX() &&
                coordinatesFromJsonArray[0].getY() == coordinatesFromJsonArray[1].getY()) {
            return Direction.HORIZONTAL;
        }
        if (coordinatesFromJsonArray[0].getY() + 1 == coordinatesFromJsonArray[1].getY() &&
                coordinatesFromJsonArray[0].getX() == coordinatesFromJsonArray[1].getX()) {
            return Direction.VERTICAL;
        }
        return Direction.WRONG;
    }
}
