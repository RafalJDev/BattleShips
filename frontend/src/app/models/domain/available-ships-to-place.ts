import {ConfigurationShip} from "./configuration-ship"

export class AvailableShipsToPlace {
  
  shipsWithSomeMast: Array<Array<ConfigurationShip>>
  
  constructor() {
    this.shipsWithSomeMast = []
  }
  
  getShipOnIndex(arrayMastIndex: number, shipInArrayIndex: number): ConfigurationShip {
    return this.shipsWithSomeMast[arrayMastIndex][shipInArrayIndex]
  }
  
  generateNoFleet() {
    // this.shipsWithSomeMast = []
    
    this.shipsWithSomeMast[0] = []
    this.shipsWithSomeMast[1] = []
    this.shipsWithSomeMast[2] = []
    this.shipsWithSomeMast[3] = []
  }
  
  generateDefaultFullFleet() {
    for (let i = 0; i < 4; i++) {
      this.shipsWithSomeMast[i] = []
      for (let j = 0; j < 4 - i; j++) {
        console.log("loop: i:" + i + " ,j:" + j)
        this.shipsWithSomeMast[i][j] = new ConfigurationShip(i + 1)
      }
    }
  }
  
  isEmpty(): boolean {
    return this.shipsWithSomeMast.length == 0
  }
  
  removeShipWithIndexes(arrayMastIndex: number, shipInArrayIndex: number) {
    this.shipsWithSomeMast[arrayMastIndex].splice(shipInArrayIndex, 1)
  }
  
  addShipWithIndexes(arrayMastIndex: number, shipInArrayIndex: number) {
    this.shipsWithSomeMast[arrayMastIndex].splice(shipInArrayIndex, 0, new ConfigurationShip(arrayMastIndex + 1))
  }
  
  addShipWithMastCount(arrayMastCount: number) {
    this.shipsWithSomeMast[arrayMastCount-1].push(new ConfigurationShip(arrayMastCount))
  }
}
