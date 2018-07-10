import {ShipArray} from "../../../models/ship/ship-array";
import {Ship} from "../../../models/ship/ship";

export class ShipOnArrayPlacer {
  
  public static placeShip(generatedShips: ShipArray, ship: Ship) {
    generatedShips.putShip(ship);
  }
}
