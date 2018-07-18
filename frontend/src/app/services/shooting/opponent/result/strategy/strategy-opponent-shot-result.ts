import {DOMCell} from "../../../../DOM/dom-cell";
import {ShotDTO} from "../../../../../models/dto/shot/shot-DTO";
import {Round} from "../../../../../models/domain/player-turn/round";

export abstract class StrategyOpponentShotResult {
  
  readonly opponentShot: ShotDTO;
  
  round: Round;
  
  protected constructor(rowIndex, columnIndex) {
    this.opponentShot = ShotDTO.ofIndexes(rowIndex, columnIndex);
  }
  
  abstract handleResult(round: Round, playerDomCell: DOMCell);
  
  abstract resultNameJustForTesting(): string;
  
  getRowIndex(): number {
    return this.opponentShot.shotCoordinate.rowIndex;
  }
  
  getColumnIndex(): number {
    return this.opponentShot.shotCoordinate.columnIndex;
  }
}
