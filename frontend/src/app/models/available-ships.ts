import {ConfigurationShip} from "./configuration-ship";

export class AvailableShips {
  
  ships: Array<ConfigurationShip>;
  
  constructor() {
    this.ships = [
      new ConfigurationShip(1), new ConfigurationShip(2)
      , new ConfigurationShip(3), new ConfigurationShip(1)
    ];
  }
  
}
