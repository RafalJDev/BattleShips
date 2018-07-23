import {BoardOfCells} from "../../../models/domain/board/board-of-cells";
import {Component, Input} from "@angular/core";
import {OpponentBoardHandler} from "../../../services/shooting/player/click-handler/opponent-board-handler.service";
import {Round} from "../../../models/domain/player-turn/round";
import {ShotSender} from "../../../rest/post/shot-sender";
import {DOMCell} from "../../../services/DOM/dom-cell";
import {OpponentAsker} from "../../../rest/get/opponent-asker";

@Component({
  selector: 'app-opponent-board',
  templateUrl: './opponent-board.component.html',
  styleUrls: ['./opponent-board.component.css']
})
export class OpponentBoardComponent {

  opponentBoard: BoardOfCells;

  shotsArray: Array<Array<string>> = [];

  round: Round;

  opponentBoardHandler: OpponentBoardHandler;

  @Input()
  playerBoardDiv: Element;

  calledFunction;

  constructor(public shotSender: ShotSender, public opponentAsker: OpponentAsker) {
    this.opponentBoard = new BoardOfCells();
  
    this.round = Round.ofNewGame(true);
    this.opponentBoardHandler = new OpponentBoardHandler(shotSender, opponentAsker, this.round);

    this.generateOpponentBoardWithWater();

    this.calledFunction = setInterval(() => {
      console.log("Callback my dear");

      if (this.playerBoardDiv != null) {

        this.opponentBoardHandler.setPlayerBoardDiv(this.playerBoardDiv);

        clearInterval(this.calledFunction);
      }

    }, 500);
  }

  generateOpponentBoardWithWater(): BoardOfCells {
    this.opponentBoard.generateBoardWithWater(10);
    return this.opponentBoard;
  }

  handleClick(boardDiv: Element, rowIndex, columnIndex) {
    let domCell = DOMCell.ofBoardAndIndexes(boardDiv, rowIndex, columnIndex);
  
    if (this.round.isPlayerRound()) {
    }
    this.opponentBoardHandler.handleShotClick(domCell);
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
