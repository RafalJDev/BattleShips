import {StrategyOpponentShotResult} from "./strategy-opponent-shot-result";
import {DOMCell} from "../../../../DOM/dom-cell";
import {Round} from "../../../../../models/domain/player-turn/round";

export class StrategyOpponentPlayerLoose extends StrategyOpponentShotResult {
  
  constructor(rowIndex, columnIndex) {
    super(rowIndex, columnIndex);
  }
  
  handleResult(round: Round, playerDomCell: DOMCell) {
    round.nextRoundIsOpponentRound();
    
    console.log("In Opponent PLAYERLOOSE handleResult!!!");
  }
  
  resultNameJustForTesting(): string {
    return "PlayerLoose";
  }
}
