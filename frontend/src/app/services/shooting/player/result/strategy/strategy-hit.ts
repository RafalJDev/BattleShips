import {StrategyShotResult} from "./strategy-shot-result"
import {DomManipulator} from "../../../../DOM/dom-manipulator"
import {Round} from "../../../../../models/domain/player-turn/round"
import {DOMCell} from "../../../../DOM/dom-cell"
import {DomClick} from "../../../../DOM/dom-click"
import {DomBufferCreator} from "../../../../DOM/dom-buffer-creator"
import {MessageTransfer} from "../../../../../views/game/message-transfer/message-transfer"

export class StrategyHit implements StrategyShotResult {
  
  handleResult(round: Round, domCell: DOMCell) {
    DomManipulator.colorCell(domCell, "darkred", "hit-locator")
    DomManipulator.setShip(domCell)
    
    DomBufferCreator.setBufferOnCellDiagonals(domCell)
    DomClick.setCellNotClickable(domCell)
    
    round.nextRoundIsPlayerRound()
    
    MessageTransfer.getInstance().playerBoardMessage = "You did HIT ship!"
  }
  
  resultNameJustForTesting(): string {
    return "HitResult"
  }
  
}
