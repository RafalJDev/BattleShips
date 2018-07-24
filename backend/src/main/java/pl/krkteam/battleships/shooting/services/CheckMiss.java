package pl.krkteam.battleships.shooting.services;

import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.cell.Mast;
import pl.krkteam.battleships.common.domain.cell.MastState;
import pl.krkteam.battleships.common.domain.cell.Missed;
import pl.krkteam.battleships.shooting.dto.result.ResultMissDTO;
import pl.krkteam.battleships.shooting.dto.result.ShotResultDTO;

public class CheckMiss implements ChainOfShotResult {

    private ChainOfShotResult chainOfShotResult = new CheckPlayerWon();

    @Override
    public ShotResultDTO getShotResult(Coordinates shotCoord, Board board) throws UnexpectedShotResultException {

        if (board.isCellEmpty(shotCoord)) {
            board.putCoordinatesAndCell(shotCoord, new Missed());
            return new ResultMissDTO();
        } else if (board.getCell(shotCoord) instanceof Missed) {
            return new ResultMissDTO();
        } else {
            final Mast mast = (Mast) board.getCell(shotCoord);
            mast.changeMastState(MastState.MAST_DESTROYED);

            return chainOfShotResult.getShotResult(shotCoord, board);
        }
    }
}
