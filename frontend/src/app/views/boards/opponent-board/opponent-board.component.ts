import {BoardOfCells} from "../../../models/domain/board/board-of-cells";
import {Component, Input} from "@angular/core";
import {OpponentBoardHandler} from "../../../services/shooting/player/click-handler/opponent-board-handler.service";
import {Round} from "../../../models/domain/player-turn/round";
import {ShotSender} from "../../../rest/post/shot-sender";
import {DOMCell} from "../../../services/DOM/dom-cell";
import {OpponentAsker} from "../../../rest/get/opponent-asker";
import {FirstTurnAsker} from "../../../rest/get/first-turn-asker";

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
  
  constructor(public shotSender: ShotSender, public opponentAsker: OpponentAsker,
              public firstTurnAsker: FirstTurnAsker) {
    
    this.opponentBoard = new BoardOfCells();
    let firstTurnRequestDone = false;
    
    this.firstTurnAsker.getOpponentResult()
        .then(result => {
      
          let firstTurn = result['message'];
          console.log("FirstTurn:" + firstTurn);
          this.round = Round.ofNewGame(firstTurn);
      
          this.opponentBoardHandler = new OpponentBoardHandler(shotSender, opponentAsker, this.round);
      
          this.generateOpponentBoardWithWater();
      
          firstTurnRequestDone = true;
        });
    
    this.calledFunction = setInterval(() => {
      console.log("Callback my dear");
  
      if (this.playerBoardDiv != null && firstTurnRequestDone) {
        
        this.opponentBoardHandler.setPlayerBoardDiv(this.playerBoardDiv);
    
        clearInterval(this.calledFunction);
    
        console.log("Inside opponent board:" + this.round.isOpponentRound()
          + " ,field value: " + this.round.playerRoundBoolean);
        this.opponentBoardHandler.handleOpponentResult();
      }
  
    }, 500);
  }
  
  generateOpponentBoardWithWater(): BoardOfCells {
    this.opponentBoard.generateBoardWithWater(10);
    return this.opponentBoard;
  }
  
  handleClick(boardDiv: Element, rowIndex, columnIndex) {
    if (this.round.isPlayerRound() && this.round.isNotWaitingForShotResult()) {
      let domCell = DOMCell.ofBoardAndIndexes(boardDiv, rowIndex, columnIndex);
      this.opponentBoardHandler.handleShotClick(domCell);
    }
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
