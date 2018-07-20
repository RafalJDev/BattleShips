package pl.krkteam.battleships.shooting.services;

import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.cell.Mast;
import pl.krkteam.battleships.common.domain.cell.MastState;
import pl.krkteam.battleships.shooting.dto.result.ResultHitDTO;
import pl.krkteam.battleships.shooting.dto.result.ShotResultDTO;

public class CheckHit implements ChainOfShotResult {
    @Override
    public ShotResultDTO getShotResult(Coordinates shotCoord, Board board) throws UnexpectedShotResultException {
        final Mast mast = (Mast) board.getCoorValue(shotCoord);
        if (mast.getMastState().equals(MastState.MAST_DESTROYED)) {
            return new ResultHitDTO();
        }
        throw new UnexpectedShotResultException(shotCoord);
    }
}
