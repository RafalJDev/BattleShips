package pl.krkteam.battleships.ships.placing.validation.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ShipJsonToCoordinates implements Converter<ShipFromJson, List<Coordinates>> {

    @Override
    public List<Coordinates> convert(ShipFromJson source) {
        if (source == null) {
            return null;
        }
        final List<Coordinates> coordinatesList = new ArrayList<>();

        CoordinatesFromJson[] coordinatesFromJsonArray = source.getCoordinates();
        Arrays.stream(coordinatesFromJsonArray).forEach(coorJson ->
                coordinatesList.add(new Coordinates(coorJson.getY(), coorJson.getX())));
        return coordinatesList;
    }
}
