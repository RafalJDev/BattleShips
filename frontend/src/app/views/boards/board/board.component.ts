import {Component, EventEmitter, Output} from "@angular/core";
import {DragShipService} from "../../../services/drag-ship/drag-ship.service";
import {BoardOfCells} from "../../../models/board/board-of-cells";
import {ShipSender} from "../../../rest/post/ship-sender";
import {ShipArray} from "../../../models/ship/ship-array";
import {Cell} from "../../../models/board/row/cell/cell";
import {ShipGenerator} from "../../../services/ship-generator/ship-generator.service";

@Component({
             selector: 'app-board-component',
             templateUrl: './board.component.html',
             styleUrls: ['./board.component.css']
           })
export class BoardComponent {
  
  showFieldBorder: boolean;
  
  @Output()
  isFleetCorrectResponse: EventEmitter<Boolean> = new EventEmitter<Boolean>();
  
  public result2: string;
  
  constructor(public dragShipService: DragShipService,
              public board: BoardOfCells,
              public shipGenerator: ShipGenerator,
              private shipSender: ShipSender) {
    
    this.board = new BoardOfCells();
    this.board.generateBoardWithWater(10);
    
    this.putShipsFromShipGenerator_random();
  }
  
  putShipsFromShipGenerator_random() {
    const shipArray = this.shipGenerator.generateShipsRandomly(this.board);
    
    this.testShipSending(shipArray);
  }
  
  testShipSending(shipArray: ShipArray) {
    
    const shipArrayJson = JSON.stringify(shipArray);
    
    this.shipSender.postShip(shipArrayJson)
        .then(result => {
          this.result2 = result['result'];
          if (this.result2 == 'true') {
            this.isFleetCorrectResponse.emit(true);
            console.log("Emiting result");
          }
        });
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
  
  handleClick() {
  
  }
}
