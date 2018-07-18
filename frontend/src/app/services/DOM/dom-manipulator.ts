import {DOMCell} from "./dom-cell";
import {DomElementGetter} from "./dom-element-getter";

export class DomManipulator {
  
  static setShip(domCell: DOMCell) {
    let innerColorDiv = DomElementGetter.getInnerColorDiv(domCell);
    
    let newShipDiv = document.createElement('div');
    newShipDiv.classList.add("ship");
    newShipDiv.classList.add("ship1");
    
    innerColorDiv.appendChild(newShipDiv);
  }
  
  static colorCell(domCell: DOMCell, color: string, innerColorClassName) {
    console.log("I will color this cell");
    //TODO attribute wrapper class
    
    const cellDiv = DomElementGetter.getCellDiv(domCell);
    const innerColorDiv = DomElementGetter.getInnerColorDiv(domCell);
    
    cellDiv.removeChild(innerColorDiv);
    
    let newInnerColorDiv = document.createElement('div');
    newInnerColorDiv.classList
                    .add("inner-color-locator");
    newInnerColorDiv.classList
                    .add(innerColorClassName);
    
    newInnerColorDiv.style.width = "35px";
    newInnerColorDiv.style.height = "35px";
    newInnerColorDiv.style.backgroundColor = color;
    
    cellDiv.appendChild(newInnerColorDiv);
  }
  
}
