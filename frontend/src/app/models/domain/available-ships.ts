import {ConfigurationShip} from "./configuration-ship"

export class AvailableShips {
  
  shipsWithSomeMast: Array<Array<ConfigurationShip>>
  
  constructor() {
    // this.shipsWithSomeMast = [
    //   new ConfigurationShip(1), new ConfigurationShip(2)
    //   , new ConfigurationShip(3), new ConfigurationShip(1),
    // ]
    
    this.shipsWithSomeMast = []
    
    for (let i = 0; i < 4; i++) {
      this.shipsWithSomeMast[i] = []
      for (let j = 0; j < 4 - i; j++) {
        console.log("loop: i:" + i + " ,j:" + j)
        this.shipsWithSomeMast[i][j] = new ConfigurationShip(i + 1)
      }
    }
  }
  
}
