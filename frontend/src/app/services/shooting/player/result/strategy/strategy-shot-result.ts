import {Round} from "../../../../../models/domain/player-turn/round"
import {DOMCell} from "../../../../DOM/dom-cell"

export interface StrategyShotResult {
  
  handleResult(round: Round, domCell: DOMCell);
  
  resultNameJustForTesting(): string;
  
}
