import {StrategyOpponentShotResult} from "./strategy-opponent-shot-result"
import {DOMCell} from "../../../../DOM/dom-cell"
import {Round} from "../../../../../models/domain/player-turn/round"
import {DomManipulator} from "../../../../DOM/dom-manipulator"
import {MessageTransfer} from "../../../../../views/game/message-transfer/message-transfer"

export class StrategyOpponentMiss extends StrategyOpponentShotResult {
  
  constructor(rowIndex, columnIndex) {
    super(rowIndex, columnIndex);
  }
  
  handleResult(round: Round, playerDomCell: DOMCell) {
    DomManipulator.colorCell(playerDomCell, "dimgray", "miss-locator");
  
    round.nextRoundIsPlayerRound();
  
    MessageTransfer.getInstance().opponentBoardMessage = "Opponent did MISS!"
  }
  
  resultNameJustForTesting(): string {
    return "ShotMiss";
  }
}
