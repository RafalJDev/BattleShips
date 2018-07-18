import {Coordinate} from "../../../models/domain/ship/coordinate/coordinate";

export class CoordinatesOnCross {
  
  northCoordinate: Coordinate;
  southCoordinate: Coordinate;
  westCoordinate: Coordinate;
  eastCoordinate: Coordinate;
  
  static ofMiddleCoordinate(coordinate: Coordinate): CoordinatesOnCross {
    let coordinatesOnCross = new CoordinatesOnCross();
    
    let rowIndex = coordinate.rowIndex;
    let columnIndex = coordinate.columnIndex;
    
    coordinatesOnCross.setNorthCoordinate(rowIndex, columnIndex);
    coordinatesOnCross.setSouthCoordinate(rowIndex, columnIndex);
    coordinatesOnCross.setWestCoordinate(rowIndex, columnIndex);
    coordinatesOnCross.setEastCoordinate(rowIndex, columnIndex);
    
    return coordinatesOnCross;
  }
  
  setNorthCoordinate(rowIndex, columnIndex) {
    this.northCoordinate = new Coordinate(rowIndex - 1, columnIndex);
  }
  
  setSouthCoordinate(rowIndex, columnIndex) {
    this.southCoordinate = new Coordinate(rowIndex + 1, columnIndex);
  }
  
  setWestCoordinate(rowIndex, columnIndex) {
    this.westCoordinate = new Coordinate(rowIndex, columnIndex - 1);
  }
  
  setEastCoordinate(rowIndex, columnIndex) {
    this.eastCoordinate = new Coordinate(rowIndex, columnIndex + 1);
  }
}
