package pl.krkteam.battleships.shooting.services;

import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.cell.Mast;
import pl.krkteam.battleships.common.domain.ship.Ship;
import pl.krkteam.battleships.common.domain.ship.ShipState;
import pl.krkteam.battleships.shooting.dto.result.ResultSunkDTO;
import pl.krkteam.battleships.shooting.dto.result.ShotResultDTO;

public class CheckSunk implements ChainOfShotResult {

    private ChainOfShotResult chainOfShotResult = new CheckHit();

    @Override
    public ShotResultDTO getShotResult(Coordinates shotCoord, Board board) throws UnexpectedShotResultException {
        final Mast mast = (Mast) board.getCell(shotCoord);
        final Ship ship = mast.getShip();
        if (ship.getShipState().equals(ShipState.DESTROYED)) {
            return new ResultSunkDTO();
        }
        return chainOfShotResult.getShotResult(shotCoord, board);
    }
}
