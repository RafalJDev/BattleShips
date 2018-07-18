import {Ship} from "../../../models/domain/ship/ship";
import {Coordinate} from "../../../models/domain/ship/coordinate/coordinate";

export class ShipCreator {
  
  public static generateShipFromCoordinates(coordinatesToCreate: number[]): Ship {
    let ship = new Ship();
    
    let coordinate: Coordinate;
    for (let index = 0; index < coordinatesToCreate.length; index += 2) {
      
      coordinate = new Coordinate(coordinatesToCreate[index + 1], coordinatesToCreate[index]);
      ship.coordinates.push(coordinate);
    }
    return ship;
  }
  
  public static createShipWithHeadCoordinate(coordinate: Coordinate,
                                             mastCount: number,
                                             isHorizontal: boolean): Ship {
    let ship = new Ship();
    let coordinateToPut: Coordinate;
    for (let index = 0; index < mastCount; index++) {
      coordinateToPut = this.calculateCoordinateToPut(coordinate, index, isHorizontal);
      
      this.putMast(ship, coordinateToPut);
    }
    return ship;
  }
  
  private static calculateCoordinateToPut(coordinate: Coordinate, index, isHorizontal): Coordinate {
    const rowIndex = this.calculateRowIndex(coordinate.rowIndex, index, isHorizontal);
    const columnIndex = this.calculateColumnIndex(coordinate.columnIndex, index, isHorizontal);
    return new Coordinate(rowIndex, columnIndex);
  }
  
  private static calculateRowIndex(rowIndex, index, isHorizontal) {
    return isHorizontal ? rowIndex : rowIndex + index;
  }
  
  private static calculateColumnIndex(columnIndex, index, isHorizontal) {
    return isHorizontal ? columnIndex + index : columnIndex;
  }
  
  private static putMast(ship: Ship, coordinate: Coordinate) {
    ship.addMast(coordinate);
  }
  
  private static logSomeDebugInfo(mastCount: number, coordinate: Coordinate, index: number) {
    console.log("I am setting ship." + " MastCount is: " + mastCount +
      " X:" + coordinate.columnIndex + " " + index + " Y:" + coordinate.rowIndex);
  }
}
