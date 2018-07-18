package pl.krkteam.battleships.result.models.dto;

public class OpponentShotSunk extends OpponentShotResult {
  
  public OpponentShotSunk(int rowIndex, int columnIndex) {
    super("ShotSunk", rowIndex, columnIndex);
  }
}
