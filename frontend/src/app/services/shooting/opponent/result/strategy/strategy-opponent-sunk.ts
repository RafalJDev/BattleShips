import {StrategyOpponentShotResult} from "./strategy-opponent-shot-result"
import {DOMCell} from "../../../../DOM/dom-cell"
import {Round} from "../../../../../models/domain/player-turn/round"
import {DomManipulator} from "../../../../DOM/dom-manipulator"
import {MessageTransfer} from "../../../../../views/game/message-transfer/message-transfer"

export class StrategyOpponentSunk extends StrategyOpponentShotResult {
  
  constructor(rowIndex, columnIndex) {
    super(rowIndex, columnIndex);
  }
  
  handleResult(round: Round, playerDomCell: DOMCell) {
    DomManipulator.colorCell(playerDomCell, "darkred", "hit-locator");
    DomManipulator.setShip(playerDomCell);
    
    round.nextRoundIsOpponentRound();
  
    MessageTransfer.getInstance().opponentBoardMessage = "Opponent SANK your ship !"
  }
  
  resultNameJustForTesting(): string {
    return "ShotSunk";
  }
}
