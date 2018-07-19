package pl.krkteam.battleships.shooting.services;

import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.cell.Mast;
import pl.krkteam.battleships.common.domain.ship.FleetState;
import pl.krkteam.battleships.common.domain.ship.Ship;
import pl.krkteam.battleships.common.domain.ship.ShipHolder;
import pl.krkteam.battleships.shooting.models.dto.result.ResultPlayerWonDTO;
import pl.krkteam.battleships.shooting.models.dto.result.ShotResultDTO;

public class CheckPlayerWon implements ChainOfShotResult {

    ChainOfShotResult chainOfShotResult=new CheckSunk();

    @Override
    public ShotResultDTO getShotResult(Coordinates shotCoord, Board board) {
        final Mast mast = (Mast) board.getCoorValue(shotCoord);
        final Ship ship = mast.getShip();
        final ShipHolder shipHolder = ship.getShipHolder();
        if (shipHolder.getFleetState().equals(FleetState.FLEET_SUNK)){
            return new ResultPlayerWonDTO();
        }
        return chainOfShotResult.getShotResult(shotCoord,board);
    }
}
