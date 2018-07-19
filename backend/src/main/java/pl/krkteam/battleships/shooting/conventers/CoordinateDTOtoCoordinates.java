package pl.krkteam.battleships.shooting.conventers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.dto.CoordinateDTO;

@Component
public class CoordinateDTOtoCoordinates implements Converter<CoordinateDTO, Coordinates> {

    @Override
    public Coordinates convert(CoordinateDTO source) {
        if (source == null) {
            return null;
        }

        return new Coordinates(source.getY(), source.getX());
    }
}
