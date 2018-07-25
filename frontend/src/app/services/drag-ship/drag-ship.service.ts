import {Injectable} from "@angular/core";
import {ConfigurationShip} from "../../models/domain/configuration-ship";

@Injectable()
export class DragShipService {

  ship: ConfigurationShip;

  constructor() {
  }
  
  dragStart($event, ship: ConfigurationShip) {
    this.ship = ship;
    console.log('Ship set');
  }
  
  dragEnter() {
  
  }
  
  dragExit($event) {

  }
  
  dragEnd() {
  
  }
  
  dragOver($event, boardDiv: Element, rowIndex: string, columnIndex: string) {

    if (this.ship != null) {

      // const innerDiv = document.createElement('div');
      //
      // let row = this.getRow(boardDiv, rowIndex);
      // let cell = this.getCell(row, rowIndex, columnIndex);
      //
      // let innerColor = this.getInnerColor(cell);
      //
      //   if (this.isThereNoShip(cell)) {
      //
      //     if (innerColor != null) {
      //     innerColor.classList.add("inner-color-black");
      //
      //     innerColor.setAttribute("width", "35px");
      //     innerColor.setAttribute("height", "35px");
      //     innerColor.setAttribute("background-color", "black");
      //
      //     console.log("just once here");
      //     innerColor.appendChild(innerDiv);
      //     }
      // }
    }
  }
}
