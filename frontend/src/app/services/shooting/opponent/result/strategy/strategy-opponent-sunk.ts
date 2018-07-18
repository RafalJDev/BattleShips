import {StrategyOpponentShotResult} from "./strategy-opponent-shot-result";
import {DOMCell} from "../../../../DOM/dom-cell";
import {Round} from "../../../../../models/domain/player-turn/round";
import {DomManipulator} from "../../../../DOM/dom-manipulator";

export class StrategyOpponentSunk extends StrategyOpponentShotResult {
  
  constructor(rowIndex, columnIndex) {
    super(rowIndex, columnIndex);
  }
  
  handleResult(round: Round, playerDomCell: DOMCell) {
    DomManipulator.colorCell(playerDomCell, "darkred", "hit-locator");
    DomManipulator.setShip(playerDomCell);
    
    round.nextRoundIsOpponentRound();
    
    console.log("In Opponent SUNK handleResult!!!");
  }
  
  resultNameJustForTesting(): string {
    return "ShotSunk";
  }
}
