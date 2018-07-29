import {Injectable} from "@angular/core"
import {ShotSender} from "../../../../rest/post/shot-sender"
import {ShotDTO} from "../../../../models/dto/shot/shot-DTO"
import {ShotResultFactory} from "../result/shot-result-factory"
import {StrategyShotResult} from "../result/strategy/strategy-shot-result"
import {Round} from "../../../../models/domain/player-turn/round"
import {DOMCell} from "../../../DOM/dom-cell"
import {DomClick} from "../../../DOM/dom-click"
import {OpponentAsker} from "../../../../rest/get/opponent-asker"
import {OpponentResponseHandler} from "../../opponent/response/handler/opponent-response-handler"
import {MessageTransfer} from "../../../../views/game/message-transfer/message-transfer"

@Injectable()
export class OpponentBoardHandler {
  
  shotSender: ShotSender
  opponentAsker: OpponentAsker
  round: Round
  
  opponentResponseHandler: OpponentResponseHandler
  
  constructor(shotSender: ShotSender, opponentAsker: OpponentAsker, round: Round) {
    this.shotSender = shotSender
    this.opponentAsker = opponentAsker
    this.round = round
    
    if (round.isPlayerRound()) {
      MessageTransfer.getInstance().opponentBoardMessage = "Now is YOUR turn !"
      MessageTransfer.getInstance().playerBoardMessage = "Now is YOUR turn !"
    } else {
      MessageTransfer.getInstance().opponentBoardMessage = "Now is OPPONENT turn !"
      MessageTransfer.getInstance().playerBoardMessage = "Now is OPPONENT turn !"
    }
  }
  
  handleShotClick(domCell: DOMCell) {
    console.log("click!")
    
    if (this.isThisCellShootAble(domCell)) {
      this.round.waitForShotResult()
      
      let shotJson = ShotDTO.ofDOMCellToJson(domCell)
      
      console.log("shotJson" + shotJson)
      
      this.shotSender.postShot(shotJson)
          .then(shotResponseJson => {
        
            console.log("shotResponseJson: " + shotResponseJson['resultMessage'])
        
            let shotStrategy: StrategyShotResult = ShotResultFactory.createStrategyForShotResultFromJson(
              shotResponseJson)
        
            console.log("shotStrategy: " + shotStrategy.resultNameJustForTesting())
        
            shotStrategy.handleResult(this.round, domCell)
        
            this.handleOpponentResult()
          })
    }
  }
  
  handleOpponentResult() {
    this.opponentResponseHandler.handleOpponentResult()
  }
  
  setPlayerBoardDiv(playerBoardDiv: Element) {
    this.opponentResponseHandler = new OpponentResponseHandler(this.round, this.opponentAsker, playerBoardDiv)
  }
  
  private isThisCellShootAble(domCell: DOMCell): boolean {
    let isCellClickable = DomClick.isCellClickAble(domCell)
    
    return this.canPlayerMakeShot() && isCellClickable
  }
  
  private canPlayerMakeShot(): boolean {
    return this.round.isPlayerRound() && this.round.isNotWaitingForShotResult()
  }
  
}
