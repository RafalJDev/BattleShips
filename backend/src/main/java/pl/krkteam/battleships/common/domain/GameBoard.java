package pl.krkteam.battleships.common.domain;

import pl.krkteam.battleships.common.domain.cell.Mast;
import pl.krkteam.battleships.common.domain.ship.Ship;
import pl.krkteam.battleships.common.domain.ship.ShipHolder;

import java.util.List;

public class GameBoard {
    private Board board = new Board(new BoardSize(10));
    private ShipHolder shipHolder = new ShipHolder();
    private volatile boolean placedFleet = false;

    public Ship createShip(List<Coordinates> coordinates) {
        Ship ship = new Ship(shipHolder);
        putMastsOnBoard(coordinates, ship);
        return ship;
    }

    public void reset() {
        board = new Board(new BoardSize(10));
        shipHolder = new ShipHolder();
    }

    private void putMastsOnBoard(List<Coordinates> coordinates, Ship ship) {
        coordinates.forEach(coor -> {
            Mast mast = new Mast(ship);
            board.putCoordinatesAndCell(coor, mast);
        });
    }

    public Board getBoard() {
        return board;
    }

    ShipHolder getShipHolder() {
        return shipHolder;
    }

    public synchronized boolean isPlacedFleet() {
        return placedFleet;
    }

    public synchronized void setPlacedFleet(boolean placedFleet) {
        this.placedFleet = placedFleet;
    }
}
