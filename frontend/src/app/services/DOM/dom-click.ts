import {DomElementGetter} from "./dom-element-getter";
import {DOMCell} from "./dom-cell";

export class DomClick {
  
  static setCellNotClickable(domCell: DOMCell) {
    let cellDiv = DomElementGetter.getCellDiv(domCell);
    
    cellDiv.classList.add("not-clickable");
  }
  
  static isCellClickAble(domCell: DOMCell) {
    let cellDiv = DomElementGetter.getCellDiv(domCell);
    
    if (cellDiv.classList.contains("not-clickable")) {
      return false;
    }
    return true;
  }
}
