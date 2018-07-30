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
    // this.shipArray.forEach(ship => {
    //   ship.coordinates.forEach(coordinate => console.log(coordinate.toString()))
    // })
    
    for (let i = 0; i < this.getShipArrayLength(); i++) {
      let currentShip = this.shipArray[i]
      for (let j = 0; j < currentShip.getMastCount(); j++) {
        if (this.isCoordinatesTheSame(currentShip.coordinates[j], coordinate)) {
          return currentShip
        }
      }
    }
    
    console.error("No ship founded ! For coordinate: y:" + coordinate.rowIndex + " x:" + coordinate.columnIndex)
    
    //todo its hype-lamba but not working :(
    // return this.shipArray.find(ship => ship.coordinates.every((corValue, curIndex, corArray) => {
    //   return corArray[curIndex].rowIndex == coordinate.rowIndex && corArray[curIndex].columnIndex == coordinate.columnIndex
    // }))
  }
  
  private isCoordinatesTheSame(currentCoordinate: Coordinate, searchedCoordinate: Coordinate): boolean {
    return currentCoordinate.rowIndex === searchedCoordinate.rowIndex &&
      currentCoordinate.columnIndex === searchedCoordinate.columnIndex
  }
  
  getShipThatContainsIndexes(rowIndex, columnIndex): Ship {
    return this.getShipThatContainsCoordinate(new Coordinate(rowIndex, columnIndex))
  }
  
  removeShip(ship: Ship) {
    var index = this.indexOfShip(ship)
    this.shipArray.splice(index, 1)
  }
  
  private indexOfShip(ship: Ship): number {
    return this.shipArray.indexOf(ship, 0)
  }
  
  getShipArrayLength(): number {
    return this.shipArray.length
  }
  
}
