import {DomDirectionSearcher} from "./dom-direction-searcher/dom-direction-searcher";
import {Coordinate} from "../../models/domain/ship/coordinate/coordinate";
import {DomCellVerifier} from "./dom-cell-verifier";
import {IndexVerification} from "../verification/index-verification";
import {DOMCell} from "./dom-cell";
import {DomClick} from "./dom-click";
import {CoordinateDirection} from "./dom-direction-searcher/coordinate-direction";
import {CoordinateVerifier} from "../verification/coordinate-verifier";
import {DomManipulator} from "./dom-manipulator";

export class DomBufferCreator {
  
  static setBufferOnShipHeadAndTail(domCell: DOMCell) {
    
    let coordinateDirection = DomDirectionSearcher.searchForDirection(domCell);
    
    this.setBufferOnHeadCoordinate(domCell, coordinateDirection);
    this.setBufferOnTailCoordinate(domCell, coordinateDirection);
  }
  
  static setBufferOnCellDiagonals(domCell: DOMCell) {
    this.setBufferOnDiagonalRows(domCell);
  }
  
  private static setBufferOnHeadCoordinate(domCell: DOMCell, coordinateDirection: CoordinateDirection) {
    let calculateNextHeadCoordinate = (passedDC: DOMCell, passedCD: CoordinateDirection): Coordinate => {
      return passedDC.coordinate.ofCoordinateForNextHead(passedCD);
    };
    
    this.setBufferOnLastDomCell(domCell, coordinateDirection, calculateNextHeadCoordinate);
  }
  
  private static setBufferOnTailCoordinate(domCell: DOMCell, coordinateDirection: CoordinateDirection) {
    let calculateNextTailCoordinate = (passedDC: DOMCell, passedCD: CoordinateDirection): Coordinate => {
      return passedDC.coordinate.ofCoordinateForNextTail(passedCD);
    };
    
    this.setBufferOnLastDomCell(domCell, coordinateDirection, calculateNextTailCoordinate);
  }
  
  private static setBufferOnLastDomCell(domCell: DOMCell,
                                        coordinateDirection: CoordinateDirection,
                                        howToCalculateNextCoordinate: (passedDC: DOMCell,
                                                                       passedCD: CoordinateDirection) => Coordinate) {
    let nextCoordinate: Coordinate = howToCalculateNextCoordinate(domCell, coordinateDirection);
    let nextDomCell;
    for (var index = 0; index < 10; index++) {
      if (CoordinateVerifier.isCoordinateNotInRange(nextCoordinate)) {
        break;
      }
      nextDomCell = domCell.ofNewCellWithCoordinate(nextCoordinate);
      
      if (DomCellVerifier.isBufferOrBeforeShotOrWaterOnCel(nextDomCell)) {
        this.setBufferOnDomCell(nextDomCell);
        break;
      }
      nextCoordinate = howToCalculateNextCoordinate(nextDomCell, coordinateDirection);
    }
  }
  
  private static setBufferOnDiagonalRows(domCell: DOMCell) {
    
    for (let rowBufferIndex = -1; rowBufferIndex <= 1; rowBufferIndex += 2) {
      let currentBufferRow = domCell.getRowIndex() + rowBufferIndex;
      this.setBufferAndNotClickableOnRow(domCell, currentBufferRow);
    }
  }
  
  private static setBufferAndNotClickableOnRow(domCell: DOMCell, currentBufferRow) {
    for (let columnBufferIndex = -1; columnBufferIndex <= 1; columnBufferIndex += 2) {
      let currentBufferColumn = domCell.getColumnIndex() + columnBufferIndex;
      
      let currentDomBufferCell = DOMCell.ofBoardAndIndexes(domCell.boardDiv, currentBufferRow, currentBufferColumn);
      
      if (IndexVerification.isOneOfIndexesNotInRegularRange(currentBufferRow, currentBufferColumn)) {
        continue;
      }
      this.setBufferOnDomCell(currentDomBufferCell);
    }
  }
  
  private static setBufferOnDomCell(domCell: DOMCell) {
    const bufferColor = "yellow";
    DomManipulator.colorCell(domCell, bufferColor, "buffer-locator");
    
    DomClick.setCellNotClickable(domCell);
  }
}
