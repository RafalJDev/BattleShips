package pl.krkteam.battleships.result.models.dto;

public class OpponentShotMiss extends OpponentShotResult {
  
  public OpponentShotMiss(int rowIndex, int columnIndex) {
    super("ShotMiss", rowIndex, columnIndex);
  }
}
