import {DOMCell} from "./dom-cell";

export class DomElementGetter {
  
  static getInnerColorDiv(domCell: DOMCell): Element {
    const cellDiv = this.getCellDiv(domCell);
    return this.getInnerElement(cellDiv, "inner-color-locator");
  }
  
  static getCellDiv(domCell: DOMCell): Element {
    const row = this.getInnerElement(domCell.boardDiv, "row" + domCell.getRowIndex());
    let squareName = "square" + domCell.getRowIndex() + domCell.getColumnIndex();
    return this.getInnerElement(row, squareName);
  }
  
  static getInnerElement(element: Element, innerElementClass: string): Element {
    const innerSquareList = element.getElementsByClassName(innerElementClass);
    const innerSquare = innerSquareList.item(0);
    
    return innerSquare;
  }
  
  static getShipDiv(domCell: DOMCell): Element {
    const innerColorDiv = this.getInnerColorDiv(domCell);
    return this.getInnerElement(innerColorDiv, "ship");
  }
}
