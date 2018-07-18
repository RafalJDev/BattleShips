import {StrategyShotResult} from "./strategy-shot-result";
import {Round} from "../../../../../models/domain/player-turn/round";
import {DomManipulator} from "../../../../DOM/dom-manipulator";
import {DOMCell} from "../../../../DOM/dom-cell";

export class StrategyMiss implements StrategyShotResult {
  
  handleResult(round: Round, domCell: DOMCell) {
    
    DomManipulator.colorCell(domCell, "#00b3ee", "water-locator");
    
    round.nextRoundIsOpponentRound();
  }
  
  resultNameJustForTesting(): string {
    return "ResultMiss";
  }
  
}
