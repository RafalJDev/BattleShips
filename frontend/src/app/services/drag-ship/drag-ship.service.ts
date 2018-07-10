import {Injectable} from "@angular/core";
import {ConfigurationShip} from "../../models/configuration-ship";

@Injectable()
export class DragShipService {
  
  ship: ConfigurationShip;
  
  constructor() {
  }
  
  onDragStart($event, ship: ConfigurationShip) {
    this.ship = ship;
    console.log('Ship set');
  }
  
  onDragEnd($event) {
  
  }
  
  dragOver($event, board: Element, rowIndex: string, columnIndex: string) {
  
    if (this.ship != null) {
    
      // innerDiv.setAttribute("background-image", "assets/ship_1.png");
      // innerDiv.setAttribute("background-color", "#00b3ee");
    
      const innerDiv = document.createElement('div');
    
      let row = this.getRow(board, rowIndex);
      let square = this.getSquare(row, rowIndex, columnIndex);
    
      for (var k = 0; k < 5; k++) {
        if (this.isThereNoShip(square)) {
          innerDiv.classList.add("ship" + this.ship.mastCount);
          console.log("just once here");
          square.appendChild(innerDiv);
          break;
        }
      }
    }
  }
  
  private getSquare(row: Element, rowIndex: string, columnIndex: string) {
    const squareWithNumber = "square" + rowIndex + columnIndex;
    let squareList: NodeListOf<Element> = row.getElementsByClassName(squareWithNumber);
    let square = squareList.item(0);
  
    //square.setAttribute("color", "#00b3ee");
    
    return square;
  }
  
  private getRow(board: Element, rowIndex: string) {
    let rowList: NodeListOf<Element> = board.getElementsByClassName("row" + rowIndex);
    let row: Element = rowList.item(0);
    return row;
  }
  
  private isThereNoShip(square: Element): boolean {
    
    for (var k = 0; k < 5; k++) {
      let elementsByClassName = square.getElementsByClassName("ship" + k);
      if (elementsByClassName.length != 0) {
        return false;
      }
    }
    return true;
  }
}
