package pl.krkteam.battleships.result.models.dto;

public class OpponentShotPlayerLoose extends OpponentShotResult {
  public OpponentShotPlayerLoose(int rowIndex, int columnIndex) {
    super("ShotPlayerLoose", rowIndex, columnIndex);
  }
}
