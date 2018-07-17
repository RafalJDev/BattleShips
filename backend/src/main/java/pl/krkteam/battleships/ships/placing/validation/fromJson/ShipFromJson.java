package pl.krkteam.battleships.ships.placing.validation.fromJson;


public class ShipFromJson {

    private CoordinatesFromJson[] coordinates;

    public ShipFromJson(CoordinatesFromJson[] coordinates) {
        this.coordinates = coordinates;
    }

    public CoordinatesFromJson[] getCoordinates() {
        return coordinates;
    }
}