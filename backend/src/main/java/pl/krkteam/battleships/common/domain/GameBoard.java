package pl.krkteam.battleships.common.domain;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.cell.Mast;
import pl.krkteam.battleships.common.domain.ship.Ship;
import pl.krkteam.battleships.common.domain.ship.ShipHolder;

import java.util.List;

@Component
public class GameBoard {
    private Board board = new Board(new BoardSize(10));
    private ShipHolder shipHolder = new ShipHolder();


    public Board getBoard() {
        return board;
    }

    public ShipHolder getShipHolder() {
        return shipHolder;
    }

    public Ship createShip(List<Coordinates> coordinates) {
        Ship ship = new Ship(shipHolder);
        coordinates.forEach(coor -> {
            Mast mast = new Mast(ship);
            board.putCoordinatesAndCell(coor, mast);
        });
        return ship;
    }
}
