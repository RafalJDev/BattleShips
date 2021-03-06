package pl.krkteam.battleships.common.domain;

import pl.krkteam.battleships.common.domain.cell.Cell;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private BoardSize boardSize;
    private Map<Coordinates, Cell> coordinatesCellMap = new HashMap<>();


    public Board(BoardSize boardSize) {
        this.boardSize = boardSize;
    }

    public Cell putCoordinatesAndCell(Coordinates coordinates, Cell cell) {
        return coordinatesCellMap.put(coordinates, cell);
    }

    public Cell getCell(Coordinates coordinates) {
        return coordinatesCellMap.get(coordinates);
    }

    public BoardSize getBoardSize() {
        return new BoardSize(boardSize.getDimensions());
    }

    Map<Coordinates, Cell> getCoordinatesCellMap() {
        return Collections.unmodifiableMap(coordinatesCellMap);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !coordinatesCellMap.containsKey(coordinates);
    }
}
