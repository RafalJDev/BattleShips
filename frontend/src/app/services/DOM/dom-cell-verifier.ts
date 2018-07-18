import {Coordinate} from "../../models/domain/ship/coordinate/coordinate";
import {DOMCell} from "./dom-cell";
import {DomElementGetter} from "./dom-element-getter";
import {CoordinateVerifier} from "../verification/coordinate-verifier";

export class DomCellVerifier {
  
  static isShipOnCellCoordinate(domCell: DOMCell, coordinate: Coordinate): boolean {
    let currentDomCell = domCell.ofNewCellWithCoordinate(coordinate);
    return CoordinateVerifier.isCoordinateInRange(coordinate) && this.isShipOnCell(currentDomCell);
  }
  
  static isShipOnCell(currentDomCell: DOMCell): boolean {
    return DomElementGetter.getShipDiv(currentDomCell) != null;
  }
  
  static isBufferOrBeforeShotOrWaterOnCel(currentDomCell: DOMCell): boolean {
    return this.isBeforeShotOnCell(currentDomCell) || this.isBufferOnCell(currentDomCell) || this.isWaterOnCell(
      currentDomCell);
  }
  
  static isBeforeShotOnCell(currentDomCell: DOMCell): boolean {
    return this.isClassOnCell(currentDomCell, "inner-color-before-shot");
  }
  
  static isBufferOnCell(currentDomCell: DOMCell): boolean {
    return this.isClassOnCell(currentDomCell, "buffer-locator");
  }
  
  private static isWaterOnCell(currentDomCell: DOMCell) {
    return this.isClassOnCell(currentDomCell, "water-locator");
  }
  
  private static isClassOnCell(domCell: DOMCell, className): boolean {
    return DomElementGetter.getInnerColorDiv(domCell)
                           .classList
                           .contains(className);
  }
}
