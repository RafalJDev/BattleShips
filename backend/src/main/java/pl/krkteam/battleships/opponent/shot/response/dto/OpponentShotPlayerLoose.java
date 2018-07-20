package pl.krkteam.battleships.opponent.shot.response.dto;

public class OpponentShotPlayerLoose extends OpponentShotResult {
  public OpponentShotPlayerLoose(int rowIndex, int columnIndex) {
    super("ShotPlayerLoose", rowIndex, columnIndex);
  }
}
