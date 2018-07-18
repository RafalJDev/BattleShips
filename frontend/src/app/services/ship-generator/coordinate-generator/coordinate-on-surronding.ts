import {Coordinate} from "../../../models/domain/ship/coordinate/coordinate";

export class CoordinatesOnSurrounding {
  //todo 6.7.2018 UGLY ASS HEL
  //it don't compile without underscore
  private _upperRowIndex;
  private _upperColumnIndex;
  private _centerRowIndex;
  private _centerColumnIndex;
  private _lowerRowIndex;
  private _lowerColumnIndex;
  
  //private on constructor is not working like in java
  private constructor() {
  }
  
  public get upperRowIndex() {
    return this._upperRowIndex;
  }
  
  public get upperColumnIndex() {
    return this._upperColumnIndex;
  }
  
  public get centerRowIndex() {
    return this._centerRowIndex;
  }
  
  public get centerColumnIndex() {
    return this._centerColumnIndex;
  }
  
  public get lowerRowIndex() {
    return this._lowerRowIndex;
  }
  
  public get lowerColumnIndex() {
    return this._lowerColumnIndex;
  }
  
  public static createCoordinatesForDirection(coordinate: Coordinate, i, isHorizontal): CoordinatesOnSurrounding {
    return isHorizontal ? CoordinatesOnSurrounding.createCoordinatesForHorizontal(coordinate, i) :
           CoordinatesOnSurrounding.createCoordinatesForVertical(coordinate, i);
  }
  
  private static createCoordinatesForHorizontal(coordinate: Coordinate, i): CoordinatesOnSurrounding {
    let coordinates = new CoordinatesOnSurrounding();
  
    const y = coordinate.rowIndex;
    const x = coordinate.columnIndex;
  
    coordinates._upperRowIndex = y - 1;
    coordinates._upperColumnIndex = x + i;
  
    coordinates._centerRowIndex = y;
    coordinates._centerColumnIndex = x + i;
  
    coordinates._lowerRowIndex = y + 1;
    coordinates._lowerColumnIndex = x + i;
    
    return coordinates;
  }
  
  private static createCoordinatesForVertical(coordinate: Coordinate, i): CoordinatesOnSurrounding {
    let coordinates = new CoordinatesOnSurrounding();
    
    const rowIndex = coordinate.rowIndex;
    const columnIndex = coordinate.columnIndex;
    
    coordinates._upperRowIndex = rowIndex + i;
    coordinates._upperColumnIndex = columnIndex - 1;
    
    coordinates._centerRowIndex = rowIndex + i;
    coordinates._centerColumnIndex = columnIndex;
    
    coordinates._lowerRowIndex = rowIndex + i;
    coordinates._lowerColumnIndex = columnIndex + 1;
    
    return coordinates;
  }
}
