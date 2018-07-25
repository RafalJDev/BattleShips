import {DOMCell} from "../dom-cell";
import {CoordinatesOnCross} from "./coordinates-on-cross";
import {CoordinateDirection} from "./coordinate-direction";
import {DomCellVerifier} from "../dom-cell-verifier";

export class DomDirectionSearcher {
  
  static searchForDirection(domCell: DOMCell): CoordinateDirection {
    let coordinatesOnCross = CoordinatesOnCross.ofMiddleCoordinate(domCell.coordinate);
    
    if (this.isShipOnNorthCell(domCell, coordinatesOnCross)) {
      let coordinateDirection = CoordinateDirection.ofNorthDirection();
      return coordinateDirection;
    } else if (this.isShipOnSouthCell(domCell, coordinatesOnCross)) {
      return CoordinateDirection.ofSouthDirection();
    } else if (this.isShipOnWestCell(domCell, coordinatesOnCross)) {
      return CoordinateDirection.ofWestDirection();
    } else if (this.isShipOnEastCell(domCell, coordinatesOnCross)) {
      return CoordinateDirection.ofEastDirection();
    } else {
      new Error("No before shot class around ! " +
        "Maybe the result (sunk) from backend is bad ?");
    }
  }
  
  private static isShipOnNorthCell(domCell: DOMCell, coordinatesOnCross: CoordinatesOnCross): boolean {
    return DomCellVerifier.isShipOnCellCoordinate(domCell, coordinatesOnCross.northCoordinate);
  }
  
  private static isShipOnSouthCell(domCell: DOMCell, coordinatesOnCross: CoordinatesOnCross): boolean {
    return DomCellVerifier.isShipOnCellCoordinate(domCell, coordinatesOnCross.southCoordinate);
  }
  
  private static isShipOnWestCell(domCell: DOMCell, coordinatesOnCross: CoordinatesOnCross): boolean {
    return DomCellVerifier.isShipOnCellCoordinate(domCell, coordinatesOnCross.westCoordinate);
  }
  
  private static isShipOnEastCell(domCell: DOMCell, coordinatesOnCross: CoordinatesOnCross): boolean {
    return DomCellVerifier.isShipOnCellCoordinate(domCell, coordinatesOnCross.eastCoordinate);
  }
  
}
