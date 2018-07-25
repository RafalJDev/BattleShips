import {StrategyOpponentShotResult} from "./strategy-opponent-shot-result";
import {DOMCell} from "../../../../DOM/dom-cell";
import {Round} from "../../../../../models/domain/player-turn/round";
import {DomManipulator} from "../../../../DOM/dom-manipulator";

export class StrategyOpponentMiss extends StrategyOpponentShotResult {
  
  constructor(rowIndex, columnIndex) {
    super(rowIndex, columnIndex);
  }
  
  handleResult(round: Round, playerDomCell: DOMCell) {
    DomManipulator.colorCell(playerDomCell, "dimgray", "miss-locator");
  
    round.nextRoundIsPlayerRound();
    
    console.log("In Opponent MISS handleResult!!!");
  }
  
  resultNameJustForTesting(): string {
    return "ShotMiss";
  }
}
