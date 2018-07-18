package pl.krkteam.battleships.result.models.dto;

public class OpponentShotHit extends OpponentShotResult {
  
  public OpponentShotHit(int rowIndex, int columnIndex) {
    super("ShotHit", rowIndex, columnIndex);
  }
  
}
