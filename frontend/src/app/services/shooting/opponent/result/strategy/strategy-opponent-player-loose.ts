import {StrategyOpponentShotResult} from "./strategy-opponent-shot-result"
import {DOMCell} from "../../../../DOM/dom-cell"
import {Round} from "../../../../../models/domain/player-turn/round"
import {MessageTransfer} from "../../../../../views/game/message-transfer/message-transfer"
import {DomManipulator} from "../../../../DOM/dom-manipulator"

export class StrategyOpponentPlayerLoose extends StrategyOpponentShotResult {
  
  constructor(rowIndex, columnIndex) {
    super(rowIndex, columnIndex);
  }
  
  handleResult(round: Round, playerDomCell: DOMCell) {
    DomManipulator.colorCell(playerDomCell, "darkred", "hit-locator");
    DomManipulator.setShip(playerDomCell);
    
    round.nextRoundIsOpponentRound();
    round.isEndOfGame = true
  
    MessageTransfer.getInstance().opponentBoardMessage = "YOU LOOSE THE GAME!!!"
  }
  
  resultNameJustForTesting(): string {
    return "PlayerLoose";
  }
}
