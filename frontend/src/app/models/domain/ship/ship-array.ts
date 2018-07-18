import {ShipCreator} from "../../../services/ship-generator/ship-creator/ship-creator";
import {Ship} from "./ship";

export class ShipArray {
  
  readonly shipArray: Array<Ship>;
  
  constructor() {
    this.shipArray = [];
  }
  
  public addShipWithCoordinates(...coordinates: number[]) {
    const ship = ShipCreator.generateShipFromCoordinates(coordinates);
    this.shipArray.push(ship);
  }
  
  public putShip(ship: Ship) {
    this.shipArray.push(ship);
  }
}
