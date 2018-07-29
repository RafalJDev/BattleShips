import {StrategyShotResult} from "./strategy-shot-result"
import {Round} from "../../../../../models/domain/player-turn/round"
import {DomManipulator} from "../../../../DOM/dom-manipulator"
import {DOMCell} from "../../../../DOM/dom-cell"
import {MessageTransfer} from "../../../../../views/game/message-transfer/message-transfer"

export class StrategyMiss implements StrategyShotResult {
  
  handleResult(round: Round, domCell: DOMCell) {
    
    DomManipulator.colorCell(domCell, "#00b3ee", "water-locator")
    
    round.nextRoundIsOpponentRound()
    
    MessageTransfer.getInstance().playerBoardMessage = "You did miss!"
  }
  
  resultNameJustForTesting(): string {
    return "ResultMiss"
  }
  
}
