import {BoardOfCells} from "../../../models/board/board-of-cells";
import {Component} from "@angular/core";
import {Coordinate} from "../../../models/ship/coordinate/coordinate";
import {ContentEnum} from "../../../models/board/row/cell/content/content";
import {OpponentBoardHandler} from "../../../services/click-handler/oppent-board-handler.service";

@Component({
             selector: 'app-opponent-board',
             templateUrl: './opponent-board.component.html',
             styleUrls: ['./opponent-board.component.css']
           })
export class OpponentBoardComponent {

  public opponentBoard: BoardOfCells;

  squareColor: string;
  clickedCoordinate: Coordinate = new Coordinate(-1, -1);
  shotsArray: Array<Array<string>> = [];
  calledFunction;
  counter: number = 1;

  constructor(public opponentBoardHandler: OpponentBoardHandler) {
    this.fillArrayWithEmpty();

    this.opponentBoard = new BoardOfCells();

    this.generateOpponentBoardWithWater();

    console.log("content:" + ContentEnum.WATER);
  }

  generateOpponentBoardWithWater(): BoardOfCells {
    this.opponentBoard.generateBoardWithWater(10);
    return this.opponentBoard;
  }

  whatColor(rowIndex, columnIndex): string {
    console.log("OpponentBoard:");
    console.log("indexes: " + rowIndex + " : " + columnIndex);
    if (this.shotsArray[rowIndex][columnIndex] === 'X') {
      console.log("shotsArray" + this.shotsArray[rowIndex][columnIndex]);
      console.log("get in");
      return "black";
    }
    console.log("color:" + this.squareColor);
    return this.squareColor;
  }

  handleClick(boardDiv: Element, rowIndex, columnIndex) {

    console.log("click!");

    this.clickedCoordinate = new Coordinate(rowIndex, columnIndex);

    this.shotsArray[rowIndex][columnIndex] = 'X';

  }

  printBoard() {
    console.log("print");
    this.shotsArray.forEach(rowArray => {
      let roww = "";
      rowArray.forEach(cell => {
        roww += cell.toString();
      });
      console.log(roww);
    });
    console.log("print");
  }

  fillArrayWithEmpty() {
    for (let i = 0; i < 10; i++) {
      this.shotsArray[i] = [];
      for (let j = 0; j < 10; j++) {
        this.shotsArray[i][j] = "O";
      }
    }
  }
}
