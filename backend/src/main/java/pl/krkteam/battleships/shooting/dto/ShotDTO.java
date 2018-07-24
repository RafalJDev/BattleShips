package pl.krkteam.battleships.shooting.dto;

import pl.krkteam.battleships.common.dto.CoordinateDTO;

public class ShotDTO {

    private CoordinateDTO shotCoordinate;

    public ShotDTO(CoordinateDTO shotCoordinate) {
        this.shotCoordinate = shotCoordinate;
    }


    public CoordinateDTO getShotCoordinate() {
        return shotCoordinate;
    }

    @Override
    public String toString() {
        return "ShotDTO{" + "shotCoordinate=" + shotCoordinate + '}';
    }
}
