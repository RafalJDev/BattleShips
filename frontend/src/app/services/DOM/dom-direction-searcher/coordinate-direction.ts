export class CoordinateDirection {
  
  rowIndexDirection;
  columnIndexDirection;
  
  static ofNorthDirection(): CoordinateDirection {
    return this.ofDirections(-1, 0);
  }
  
  static ofSouthDirection(): CoordinateDirection {
    return this.ofDirections(1, 0);
  }
  
  static ofWestDirection(): CoordinateDirection {
    return this.ofDirections(0, -1);
  }
  
  static ofEastDirection(): CoordinateDirection {
    return this.ofDirections(0, 1);
  }
  
  private static ofDirections(rowDirection, columnDirection): CoordinateDirection {
    let coordinateDirection = new CoordinateDirection();
    coordinateDirection.rowIndexDirection = rowDirection;
    coordinateDirection.columnIndexDirection = columnDirection;
    return coordinateDirection;
  }
  
}
