package pl.krkteam.battleships.common.dto;
public class CoordinateDTO {

  private int columnIndex;
  private int rowIndex;

  public CoordinateDTO(int x, int y) {
    this.columnIndex = x;
    this.rowIndex = y;
  }

  @Override
  public String toString() {
    return "CoordinateDTOS{" +
       "x=" + columnIndex +
       ", y=" + rowIndex +
       '}';
  }

  public int getX() {
    return columnIndex;
  }

  public int getY() {
    return rowIndex;
  }
}