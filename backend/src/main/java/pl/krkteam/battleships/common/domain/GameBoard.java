package pl.krkteam.battleships.common.domain;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.BoardSize;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.cell.mastState.PresentMast;
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
        Ship ship = new Ship();
        coordinates.forEach(coor -> {
            PresentMast presentMast = new PresentMast();
            ship.addMast(presentMast);
            board.putCoordinatesAndCell(coor, presentMast);

        });
        shipHolder.addShip(ship);
        return ship;
    }
}
