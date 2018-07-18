import {Coordinate} from "../../models/domain/ship/coordinate/coordinate";
import {CoordinateDirection} from "./dom-direction-searcher/coordinate-direction";

export class DOMCell {
  
  boardDiv: Element;
  coordinate: Coordinate;
  
  static ofBoardAndIndexes(boardDiv: Element, rowIndex, columnIndex): DOMCell {
    let domCell = new DOMCell();
    domCell.boardDiv = boardDiv;
    domCell.coordinate = new Coordinate(rowIndex, columnIndex);
    return domCell;
  }
  
  ofNewHeadCell(coordinateDirection: CoordinateDirection): DOMCell {
    return this.ofNewCellWithCoordinate(this.coordinate.ofCoordinateForNextHead(coordinateDirection));
  }
  
  ofNewNextTailCell(coordinateDirection: CoordinateDirection) {
    return this.ofNewCellWithCoordinate(this.coordinate.ofCoordinateForNextTail(coordinateDirection));
  }
  
  ofNewCellWithCoordinate(coordinate: Coordinate): DOMCell {
    return this.ofNewCellWithIndexes(coordinate.rowIndex, coordinate.columnIndex);
  }
  
  getRowIndex() {
    return this.coordinate.rowIndex;
  }
  
  getColumnIndex() {
    return this.coordinate.columnIndex;
  }
  
  private ofNewCellWithIndexes(rowIndex, columnIndex): DOMCell {
    let domCell = new DOMCell();
    domCell.boardDiv = this.boardDiv;
    domCell.coordinate = new Coordinate(rowIndex, columnIndex);
    return domCell;
  }
  
}
