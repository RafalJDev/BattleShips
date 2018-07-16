import {Component, Input} from "@angular/core";
import {DragShipService} from "../../../services/drag-ship/drag-ship.service";
import {BoardOfCells} from "../../../models/board/board-of-cells";
import {Cell} from "../../../models/board/row/cell/cell";
import {ShipGenerator} from "../../../services/ship-generator/ship-generator.service";
import {ContentEnum} from "../../../models/board/row/cell/content/content";

@Component({
             selector: 'app-board-component',
             templateUrl: './board.component.html',
             styleUrls: ['./board.component.css']
           })
export class BoardComponent {

  squareColor: string;

  @Input()
  public board: BoardOfCells;

  @Input()
  isOpponentBoard: boolean;

  counter: number = 1;

  constructor(public dragShipService: DragShipService,
              public shipGenerator: ShipGenerator) {

    console.log("log:" + this.isOpponentBoard);
    console.log("content:" + ContentEnum.WATER);
  }


  putShipsFromShipGenerator_manual() {
    this.shipGenerator.generateShipsManually();

    const ships = this.shipGenerator.generatedShips.shipArray;
    ships.forEach(ship => {
      ship.coordinates.forEach(coordinate => {
        const column = coordinate.rowIndex;
        const row = coordinate.columnIndex;

        this.board[column][row] = Cell.ofShip();
      });
    });
  }

}
