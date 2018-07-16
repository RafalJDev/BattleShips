package pl.krkteam.battleships.ships.placing.validation.fromJson;

public class CoordinatesFromJson {

    private int y;
    private int x;

    public CoordinatesFromJson(int y, int x) {
        this.y = y;
        this.x = x;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}