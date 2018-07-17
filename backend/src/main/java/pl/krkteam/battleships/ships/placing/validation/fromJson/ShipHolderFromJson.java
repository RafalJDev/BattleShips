package pl.krkteam.battleships.ships.placing.validation.fromJson;

import java.util.ArrayList;
import java.util.List;

public class ShipHolderFromJson {
    private List<ShipFromJson> shipFromJsonList = new ArrayList<>();

    public void addShipFromJson(ShipFromJson shipFromJson) {
        shipFromJsonList.add(shipFromJson);
    }

    public List<ShipFromJson> getShipFromJsonList() {
        return shipFromJsonList;
    }
}
