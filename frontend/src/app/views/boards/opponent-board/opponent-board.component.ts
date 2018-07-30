import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {Component, Input, OnDestroy} from "@angular/core"
import {OpponentBoardHandler} from "../../../services/shooting/player/click-handler/opponent-board-handler.service"
import {Round} from "../../../models/domain/player-turn/round"
import {DOMCell} from "../../../services/DOM/dom-cell"
import {FirstRoundAsker} from "../../../rest/get/first-round-asker.service"
import {OpponentAsker} from "../../../rest/get/opponent-asker"
import {ShotSender} from "../../../rest/post/shot-sender"
import {MessageTransfer} from "../../game/message-transfer/message-transfer"

@Component({
             selector: 'app-opponent-board',
             templateUrl: './opponent-board.component.html',
             styleUrls: ['./opponent-board.component.css'],
           })
export class OpponentBoardComponent implements OnDestroy {
  
  opponentBoard: BoardOfCells
  
  round: Round
  
  opponentBoardHandler: OpponentBoardHandler
  
  @Input()
  playerBoardDiv: Element
  
  playerBoardWaiter
  
  messageTransfer: MessageTransfer = MessageTransfer.getInstance()
  
  private firstTurnRequestDone = false
  
  constructor(private firstRoundAsker: FirstRoundAsker, private shotSender: ShotSender,
              private opponentAsker: OpponentAsker) {
    
    this.opponentBoard = new BoardOfCells()
    
    this.askWhichPlayerIsFirst(shotSender, opponentAsker)
    
    this.waitForPlayerBoardDivAndFirstTurnRequest()
  }
  
  waitForPlayerBoardDivAndFirstTurnRequest() {
    this.playerBoardWaiter = setInterval(() => {
      console.log("Callback my dear")
      
      if (this.isBoardAndRequestDone()) {
        
        this.opponentBoardHandler.setPlayerBoardDiv(this.playerBoardDiv)
        
        clearInterval(this.playerBoardWaiter)
        
        console.log("Inside opponent board:" + this.round.isOpponentRound()
          + " ,field value: " + this.round.playerRoundBoolean)
        this.opponentBoardHandler.handleOpponentResult()
      }
    }, 500)
  }
  
  ngOnDestroy() {
    clearInterval(this.playerBoardWaiter)
  }
  
  askWhichPlayerIsFirst(shotSender: ShotSender, opponentAsker: OpponentAsker) {
    this.firstRoundAsker.getFirstRoundResult()
        .then(result => {
      
          console.log("done Ask Start")
      
          let firstRound = result['message']
          console.log("Is player first turn:" + firstRound)
          this.round = Round.ofNewGame(firstRound)
      
          this.opponentBoardHandler = new OpponentBoardHandler(shotSender, opponentAsker, this.round)
      
          this.opponentBoard.generateBoardWithWater(10)
      
          this.firstTurnRequestDone = true
      
          console.log("done Ask End")
        })
  }
  
  private isBoardAndRequestDone() {
    return this.playerBoardDiv != null && this.firstTurnRequestDone
  }
  
  handleClick(boardDiv: Element, rowIndex, columnIndex) {
    console.log("rowIndex: " + rowIndex + " columnIndex: " + columnIndex)
    if (this.round.isPlayerClickPossible()) {
      let domCell = DOMCell.ofBoardAndIndexes(boardDiv, rowIndex, columnIndex)
      this.opponentBoardHandler.handleShotClick(domCell)
    }
  }
  
}
