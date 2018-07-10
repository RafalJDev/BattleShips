import {Coordinate} from "./coordinate/coordinate";

export class Ship {
  readonly coordinates: Array<Coordinate>;
  
  //private is Not working like in java
  constructor() {
    this.coordinates = [];
  }
  
  public addMast(coordinate: Coordinate) {
    this.coordinates.push(coordinate);
  }
}
