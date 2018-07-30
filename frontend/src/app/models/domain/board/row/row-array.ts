import {Cell} from "./cell/cell"

export class RowArray {
  
  row: Array<Cell>;
  
  constructor() {
    this.row = [];
  }
  
  isShipHere(columnIndex): boolean {
    return this.row[columnIndex].isShipHere();
  }
  
  putShipOnCell(columnIndex) {
    this.row[columnIndex] = Cell.ofShip();
  }
  
  putWaterOnCell(columnIndex) {
    this.row[columnIndex] = Cell.ofWater();
  }
  
}
