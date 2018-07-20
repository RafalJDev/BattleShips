package pl.krkteam.battleships.shooting.services;

import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.shooting.models.dto.result.ShotResultDTO;

public interface ChainOfShotResult {
    ShotResultDTO getShotResult(Coordinates shotCoord, Board board) throws UnexpectedShotResultException;
}
