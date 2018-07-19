package pl.krkteam.battleships.shooting.services;

import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.cell.Mast;
import pl.krkteam.battleships.common.domain.ship.Ship;
import pl.krkteam.battleships.common.domain.ship.ShipState;
import pl.krkteam.battleships.shooting.models.dto.result.ResultHitDTO;
import pl.krkteam.battleships.shooting.models.dto.result.ResultSunkDTO;
import pl.krkteam.battleships.shooting.models.dto.result.ShotResultDTO;

public class CheckSunk implements ChainOfShotResult {

    @Override
    public ShotResultDTO getShotResult(Coordinates shotCoord, Board board) {
        final Mast mast = (Mast) board.getCoorValue(shotCoord);
        final Ship ship = mast.getShip();
        if (ship.getShipState().equals(ShipState.DESTROYED)) {
            return new ResultSunkDTO();
        }
        return new ResultHitDTO();
    }
}
