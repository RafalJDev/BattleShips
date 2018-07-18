package pl.krkteam.battleships.result.models.dto;

import pl.krkteam.battleships.common.dto.CoordinateDTO;
import pl.krkteam.battleships.shooting.models.dto.ShotDTO;

public abstract class OpponentShotResult {
  
  public String opponentShotResult;
  
  ShotDTO opponentShotDTO;
  
  public OpponentShotResult(String opponentShotResult, int rowIndex, int columnIndex) {
    this.opponentShotResult = opponentShotResult;
    final CoordinateDTO shotCoordinate = new CoordinateDTO(rowIndex, columnIndex);
    this.opponentShotDTO = new ShotDTO(shotCoordinate);
  }
}
