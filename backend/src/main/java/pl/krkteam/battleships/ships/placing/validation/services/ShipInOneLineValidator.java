package pl.krkteam.battleships.ships.placing.validation.services;

import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

class ShipInOneLineValidator {
    boolean isShipInOneLine(ShipFromJson shipFromJson, Direction direction) {
        switch (direction) {
            case ONE_MAST:
                return true;
            case VERTICAL:
                return checkLineVertical(shipFromJson);
            case HORIZONTAL:
                return checkLineHorizontal(shipFromJson);
            default:
                return false;
        }

    }


    private boolean checkLineVertical(ShipFromJson shipFromJson) {
        CoordinatesFromJson[] coordinatesFromJsonArray = shipFromJson.getCoordinates();
        int previousY = coordinatesFromJsonArray[0].getY();
        int X = coordinatesFromJsonArray[0].getX();
        for (int i = 1; i < coordinatesFromJsonArray.length; i++) {
            if (!(coordinatesFromJsonArray[i].getY() == previousY + 1) ||
                    !(coordinatesFromJsonArray[i].getX() == X)) {
                return false;
            }
            previousY++;
        }
        return true;
    }

    private boolean checkLineHorizontal(ShipFromJson shipFromJson) {
        CoordinatesFromJson[] coordinatesFromJsonArray = shipFromJson.getCoordinates();
        int previousX = coordinatesFromJsonArray[0].getX();
        int Y = coordinatesFromJsonArray[0].getY();
        for (int i = 1; i < coordinatesFromJsonArray.length; i++) {
            if (!(coordinatesFromJsonArray[i].getX() == previousX + 1) ||
                    !(coordinatesFromJsonArray[i].getY() == Y)) {
                return false;
            }
            previousX++;
        }
        return true;
    }

}
