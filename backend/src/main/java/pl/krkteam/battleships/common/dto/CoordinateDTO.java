package pl.krkteam.battleships.common.dto;
public class CoordinateDTO {

  private int x;
  private int y;

  public CoordinateDTO(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "CoordinateDTOS{" +
       "x=" + x +
       ", y=" + y +
       '}';
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}