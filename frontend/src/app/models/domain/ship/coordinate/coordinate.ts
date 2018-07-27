import {CoordinateDirection} from "../../../../services/DOM/dom-direction-searcher/coordinate-direction"

export class Coordinate {
  //TODO probablu changing coordinate to mast, and mast will have more properties
  readonly rowIndex: number
  readonly columnIndex: number
  
  constructor(rowIndex, columnIndex) {
    this.rowIndex = rowIndex
    this.columnIndex = columnIndex
  }
  
  ofCoordinateForNextHead(coordinateDirection: CoordinateDirection): Coordinate {
    let headRowIndex = this.rowIndex + coordinateDirection.rowIndexDirection
    let headColumnIndex = this.columnIndex + coordinateDirection.columnIndexDirection
    return new Coordinate(headRowIndex, headColumnIndex)
  }
  
  ofCoordinateForNextTail(coordinateDirection: CoordinateDirection): Coordinate {
    
    console.log("Coordinate for next tail row: " + this.rowIndex, " ", coordinateDirection.rowIndexDirection)
    console.log("Coordinate for next tail column: " + this.columnIndex, " ", coordinateDirection.columnIndexDirection)
    
    let headRowIndex = this.rowIndex - coordinateDirection.rowIndexDirection
    let headColumnIndex = this.columnIndex - coordinateDirection.columnIndexDirection
    
    console.log("headRowIndex:" + headRowIndex)
    console.log("headColumnIndex:" + headColumnIndex)
    return new Coordinate(headRowIndex, headColumnIndex)
  }
  
  toString(): string {
    return "rowIndex: " + this.rowIndex + " columnIndex: " + this.columnIndex
  }
}
