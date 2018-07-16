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

  dragOver($event, boardDiv: Element, rowIndex: string, columnIndex: string) {

    if (this.ship != null) {

      const innerDiv = document.createElement('div');

      let row = this.getRow(boardDiv, rowIndex);
      let cell = this.getCell(row, rowIndex, columnIndex);

      let innerColor = this.getInnerColor(cell);

        if (this.isThereNoShip(cell)) {

          if (innerColor != null) {
          innerColor.classList.add("inner-color-black");

          innerColor.setAttribute("width", "35px");
          innerColor.setAttribute("height", "35px");
          innerColor.setAttribute("background-color", "black");

          console.log("just once here");
          innerColor.appendChild(innerDiv);
          }
      }
    }
  }

  private getInnerColor(cell: Element) {
    const innerSquareList = cell.getElementsByClassName("inner-color");

    const innerSquare = innerSquareList.item(0);

    return innerSquare;
  }

  private getCell(row: Element, rowIndex: string, columnIndex: string) {
    const squareWithNumber = "square" + rowIndex + columnIndex;
    let squareList: NodeListOf<Element> = row.getElementsByClassName(squareWithNumber);
    let square = squareList.item(0);
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
