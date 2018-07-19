package pl.krkteam.battleships.shooting.controller.mocking.as.hell;

import org.springframework.stereotype.Service;
import pl.krkteam.battleships.common.dto.CoordinateDTO;
import pl.krkteam.battleships.common.dto.ShipDTO;

@Service()
public class MockedBoard {

    public ShipDTO[] shipDTOS;

    public int hittedMastCountForFirstShip = 0;
    public int hittedMastCountForSecondShip = 0;

    public MockedBoard() {

        shipDTOS = new ShipDTO[2];


        CoordinateDTO[] coordinatesFirst = new CoordinateDTO[4];

        coordinatesFirst[0] = new CoordinateDTO(2, 1);
        coordinatesFirst[1] = new CoordinateDTO(3, 1);
        coordinatesFirst[2] = new CoordinateDTO(4, 1);
        coordinatesFirst[3] = new CoordinateDTO(5, 1);

        ShipDTO firstShipDTO = new ShipDTO(coordinatesFirst);


        CoordinateDTO[] coordinatesSecond = new CoordinateDTO[3];

        coordinatesSecond[0] = new CoordinateDTO(0, 3);
        coordinatesSecond[1] = new CoordinateDTO(0, 4);
        coordinatesSecond[2] = new CoordinateDTO(0, 5);
        ShipDTO secondShipDTO = new ShipDTO(coordinatesSecond);

        shipDTOS[0] = firstShipDTO;
        shipDTOS[1] = secondShipDTO;
    }

    public boolean isThereSuchMastOnFirstShip(CoordinateDTO shotCoordinateDTO) {
        for (int i = 0; i < shipDTOS[0].getCoordinates().length; i++) {
            if (shipDTOS[0].getCoordinates()[i].equals(shotCoordinateDTO)) {
                return true;
            }
        }
        return false;
    }

    public boolean isThereSuchMastOnSecondShip(CoordinateDTO shotCoordinateDTO) {
        for (int i = 0; i < shipDTOS[1].getCoordinates().length; i++) {
            if (shipDTOS[1].getCoordinates()[i].equals(shotCoordinateDTO)) {
                return true;
            }
        }
        return false;
    }
}