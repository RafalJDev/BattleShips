package pl.krkteam.battleships.ships.placing.validation.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.dto.CoordinateDTO;
import pl.krkteam.battleships.common.dto.ShipDTO;
import pl.krkteam.battleships.common.dto.ShipHolderDTO;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipHolderFromJson;

import java.util.Arrays;

@Component
public class ShipsToShipHolder implements Converter<ShipHolderDTO, ShipHolderFromJson> {
    private ShipHolderFromJson shipHolderFromJson;

    @Override
    public ShipHolderFromJson convert(ShipHolderDTO source) {

        shipHolderFromJson = new ShipHolderFromJson();

        if (source == null) {
            return null;
        }

        final ShipDTO[] shipDTOArray = source.getShipArray();
        Arrays.stream(shipDTOArray).forEach(ship -> {
            ShipFromJson shipFromJson = convertShip(ship);
            shipHolderFromJson.addShipFromJson(shipFromJson);
        });

        return shipHolderFromJson;
    }

    private ShipFromJson convertShip(ShipDTO shipDTO) {
        CoordinatesFromJson[] coordinatesFromJsonArray = convertCoordinates(shipDTO.getCoordinates());
        return new ShipFromJson(coordinatesFromJsonArray);
    }

    private CoordinatesFromJson[] convertCoordinates(CoordinateDTO[] coordinateDTOS) {
        CoordinatesFromJson[] coordinatesFromJsonArray = new CoordinatesFromJson[coordinateDTOS.length];
        for (int i = 0; i < coordinateDTOS.length; i++) {
            coordinatesFromJsonArray[i] = new CoordinatesFromJson(coordinateDTOS[i].getY(), coordinateDTOS[i].getX());
        }
        return coordinatesFromJsonArray;
    }
}
