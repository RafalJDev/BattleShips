import {StrategyShotResult} from "./strategy-shot-result"
import {DOMCell} from "../../../../DOM/dom-cell"
import {Round} from "../../../../../models/domain/player-turn/round"
import {DomClick} from "../../../../DOM/dom-click"
import {DomManipulator} from "../../../../DOM/dom-manipulator"
import {DomBufferCreator} from "../../../../DOM/dom-buffer-creator"
import {MessageTransfer} from "../../../../../views/game/message-transfer/message-transfer"

export class StrategyPlayerWin implements StrategyShotResult {
  
  handleResult(round: Round, domCell: DOMCell) {
    DomManipulator.colorCell(domCell, "darkred", "hit-locator")
    DomManipulator.setShip(domCell)
    
    DomBufferCreator.setBufferOnCellDiagonals(domCell)
    DomClick.setCellNotClickable(domCell)
    
    DomBufferCreator.setBufferOnShipHeadAndTail(domCell)
    
    round.nextRoundIsPlayerRound()
    round.isEndOfGame = true
    
    MessageTransfer.getInstance().playerBoardMessage = "YOU WIN THE GAME!"
  }
  
  resultNameJustForTesting(): string {
    return "ResultPlayerWin"
  }
  
}
