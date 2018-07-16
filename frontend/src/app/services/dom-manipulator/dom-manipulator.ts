export class DomManipulator {

  public static setShip(boardDiv: Element, rowIndex, columnIndex) {
    const shipDiv = this.getShipDiv(boardDiv, rowIndex, columnIndex);

    shipDiv.classList.add("ship1");
  }

  public static colorCell(boardDiv: Element, rowIndex, columnIndex, color: string) {
    const innerColor = this.getInnerColorDiv(boardDiv, rowIndex, columnIndex);

    console.log("I will color this cell:   ROW: " + rowIndex + "   COLUMN: " + columnIndex);
    //TODO attribute wrapper class

    const cellDiv = this.getCellDiv(boardDiv, rowIndex, columnIndex);
    const innerColorDiv = this.getInnerColorDiv(boardDiv, rowIndex, columnIndex);

    cellDiv.removeChild(innerColorDiv);

    let newInnerColorDiv = document.createElement('div');
    newInnerColorDiv.classList.add("inner-color-hit");
    newInnerColorDiv.classList.add("inner-color-locator");


    let newShipDiv = document.createElement('div');
    newShipDiv.classList.add("ship");

    newInnerColorDiv.appendChild(newShipDiv);

    cellDiv.appendChild(newInnerColorDiv);
  }

  public static getShipDiv(boardDiv: Element, rowIndex, columnIndex): Element {
    const innerColorDiv = this.getInnerColorDiv(boardDiv, rowIndex, columnIndex);
    return this.getInnerElement(innerColorDiv, "ship");
  }

  private static getInnerColorDiv(boardDiv: Element, rowIndex, columnIndex): Element {
    const cell = this.getCellDiv(boardDiv, rowIndex, columnIndex);
    return this.getInnerElement(cell, "inner-color-locator");
  }

  private static getCellDiv(boardDiv: Element, rowIndex, columnIndex): Element {
    const row = this.getInnerElement(boardDiv, "row" + rowIndex);
    return this.getInnerElement(row, "square" + rowIndex + columnIndex);
  }

  private static getInnerElement(element: Element, innerElementClass: string): Element {
    const innerSquareList = element.getElementsByClassName(innerElementClass);
    const innerSquare = innerSquareList.item(0);

    return innerSquare;
  }
}
