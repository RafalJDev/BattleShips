import {Injectable} from "@angular/core"
import {ConfigurationShip} from "../../models/domain/configuration-ship"

@Injectable()
export class DragShipService {

  ship: ConfigurationShip;

  constructor() {
  }
  
  dragStart($event, ship: ConfigurationShip) {
    this.ship = ship;
    console.log('Ship set');
  }
  
  dragEnter() {
  
  }
  
  dragExit($event) {

  }
  
  dragEnd() {
  
  }
  
  dragOver($event, boardDiv: Element, rowIndex: string, columnIndex: string) {

    if (this.ship != null) {
    
    }
  }
}
