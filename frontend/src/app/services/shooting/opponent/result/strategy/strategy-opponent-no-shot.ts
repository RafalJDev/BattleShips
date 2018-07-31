import {StrategyOpponentShotResult} from "./strategy-opponent-shot-result"
import {DOMCell} from "../../../../DOM/dom-cell"
import {Round} from "../../../../../models/domain/player-turn/round"
import {MessageTransfer} from "../../../../../views/game/message-transfer/message-transfer"

export class StrategyOpponentNoShot extends StrategyOpponentShotResult {
  
  constructor(rowIndex, columnIndex) {
    super(rowIndex, columnIndex);
  }
  
  handleResult(round: Round, playerDomCell: DOMCell) {
    round.nextRoundIsOpponentRound();
  
    MessageTransfer.getInstance().opponentBoardMessage = "Wait for your turn"
  }
  
  resultNameJustForTesting(): string {
    return "NoShot";
  }
}
