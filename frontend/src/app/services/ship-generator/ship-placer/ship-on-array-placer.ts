import {ShipArray} from "../../../models/domain/ship/ship-array";
import {Ship} from "../../../models/domain/ship/ship";

export class ShipOnArrayPlacer {
  
  public static placeShip(generatedShips: ShipArray, ship: Ship) {
    generatedShips.putShip(ship);
  }
}
