package pl.krkteam.battleships.shooting.services;

import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.shooting.dto.ShotDTO;
import pl.krkteam.battleships.shooting.dto.result.ShotResultDTO;

public interface ShotResultCheckerService {
    ShotResultDTO checkShotResult(ShotDTO shotDTO, GameBoard gameBoard);
}
