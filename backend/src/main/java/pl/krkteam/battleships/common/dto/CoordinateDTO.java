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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CoordinateDTO)) {
            return false;
        }
        CoordinateDTO input = (CoordinateDTO) obj;
        return (this.columnIndex == input.columnIndex && this.rowIndex == input.rowIndex);
    }

    public int getX() {
        return columnIndex;
    }

    public int getY() {
        return rowIndex;
    }
}
