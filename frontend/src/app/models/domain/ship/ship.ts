import {Coordinate} from "./coordinate/coordinate"

export class Ship {
  readonly coordinates: Array<Coordinate>
  
  //private is Not working like in java
  constructor() {
    this.coordinates = []
  }
  
  public addMast(coordinate: Coordinate) {
    this.coordinates.push(coordinate)
  }
  
  getMastCount(): number {
    return this.coordinates.length
  }
  
  isShipOnHorizontal(): boolean {
    return this.isOneMast() ? true : !this.isFirstAndSecondColumnIndexTheSame()
  }
  
  private isFirstAndSecondColumnIndexTheSame(): boolean {
    return this.coordinates[0].columnIndex == this.coordinates[1].columnIndex
  }
  
  isOneMast(): boolean {
    return this.getMastCount() == 1
  }
  
}
