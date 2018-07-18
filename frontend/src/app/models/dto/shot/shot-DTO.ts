import {Coordinate} from "../../domain/ship/coordinate/coordinate";
import {DOMCell} from "../../../services/DOM/dom-cell";

export class ShotDTO {
  
  shotCoordinate: Coordinate;
  
  static ofIndexes(rowIndex, columnIndex): ShotDTO {
    let shot = new ShotDTO();
    shot.shotCoordinate = new Coordinate(rowIndex, columnIndex);
    return shot;
  }
  
  static ofDOMCell(domCell: DOMCell) {
    return this.ofIndexes(domCell.getRowIndex(), domCell.getColumnIndex());
  }
  
  static ofDOMCellToJson(domCell: DOMCell) {
    return JSON.stringify(this.ofDOMCell(domCell));
  }
}
