import {ShotResult} from "./shot-result";
import {DomManipulator} from "../../../../services/dom-manipulator/dom-manipulator";

export class ResultHit implements ShotResult {
  
  handleResult(boardDiv: Element, rowIndex, columnIndex) {
    DomManipulator.colorCell(boardDiv, rowIndex, columnIndex, "darkred");
    DomManipulator.setShip(boardDiv, rowIndex, columnIndex);
  }
  
}
