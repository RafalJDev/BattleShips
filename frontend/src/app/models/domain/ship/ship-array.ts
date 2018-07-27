import {ShipCreator} from "../../../services/ship-generator/ship-creator/ship-creator"
import {Ship} from "./ship"
import {Coordinate} from "./coordinate/coordinate"

export class ShipArray {
  
  readonly shipArray: Array<Ship>
  
  constructor() {
    this.shipArray = []
  }
  
  addShipWithCoordinates(...coordinates: number[]) {
    const ship = ShipCreator.generateShipFromCoordinates(coordinates)
    this.shipArray.push(ship)
  }
  
  putShip(ship: Ship) {
    this.shipArray.push(ship)
  }
  
  getShipThatContainsCoordinate(coordinate: Coordinate): Ship {
    console.log("herree")
    
    this.shipArray.forEach(ship => {
      ship.coordinates.forEach(coordinate => console.log(coordinate.toString()))
    })
    
    return this.shipArray.find(ship => ship.coordinates.every((corValue, curIndex, corArray) => {
      return corArray[curIndex].rowIndex == coordinate.rowIndex || corArray[curIndex].columnIndex == coordinate.columnIndex
    }))
  }
  
  removeShip(ship: Ship) {
    var index = this.indexOfShip(ship)
    this.shipArray.splice(index, 1)
  }
  
  private indexOfShip(ship: Ship): number {
    return this.shipArray.indexOf(ship, 0)
  }
  
}
