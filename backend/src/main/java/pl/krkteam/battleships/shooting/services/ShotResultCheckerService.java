package pl.krkteam.battleships.shooting.services;

import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.shooting.models.dto.ShotDTO;
import pl.krkteam.battleships.shooting.models.dto.result.ShotResultDTO;

public interface ShotResultCheckerService {
    ShotResultDTO checkShotResult(ShotDTO shotDTO, GameBoard gameBoard);
}
