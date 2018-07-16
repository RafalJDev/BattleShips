package pl.krkteam.battleships.common.domain;

import java.util.Objects;

public class Coordinates {
    private final int y;
    private final int x;

    public Coordinates(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return y == that.y &&
                x == that.x;
    }

    @Override
    public int hashCode() {

        return Objects.hash(y, x);
    }
}
