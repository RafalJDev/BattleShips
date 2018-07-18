import {RowArray} from "./row/row-array";

export class BoardOfCells {
  //TODO maybe CellArray ?
  board: Array<RowArray>;
  
  constructor() {
    this.board = [];
  }
  
  //TODO 6.7.2018 put rest methods in some service class
  
  public generateBoardWithWater(boardSize: number) {
    var rowIndex: number;
    for (rowIndex = 0; rowIndex < boardSize; rowIndex++) {
      this.board[rowIndex] = new RowArray();
      this.putWaterForEachCellInRow(boardSize, rowIndex);
    }
  }
  
  public getRow(rowIndex): RowArray {
    return this.board[rowIndex];
  }
  
  public isShipOnCell(rowIndex, columnIndex): boolean {
    return this.getRow(rowIndex).isShipHere(columnIndex);
  }
  
  public putShipOnCell(rowIndex, columnIndex) {
    this.getRow(rowIndex).putShipOnCell(columnIndex);
  }
  
  public getBoardSize(): number {
    return this.board.length;
  }
  
  private putWaterForEachCellInRow(boardSize: number, rowIndex) {
    var columnIndex: number;
    for (columnIndex = 0; columnIndex < boardSize; columnIndex++) {
      this.putWaterOnCell(rowIndex, columnIndex);
    }
  }
  
  private putWaterOnCell(rowIndex, columnIndex) {
    this.getRow(rowIndex).putWaterOnCell(columnIndex);
  }
  
  private printBoardToConsole() {
    console.log("print board");
    this.board.forEach(rowArray => {
      let tempRow = '';
      rowArray.row.forEach(cell => {
        if (cell.isWaterHere()) {
          tempRow += "X";
        }
      });
      console.log(tempRow);
    });
    console.log("print board");
  }
}