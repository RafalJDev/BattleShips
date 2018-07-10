export class Coordinate {
  //TODO probablu changing coordinate to mast, and mast will have more properties
  readonly rowIndex: number;
  readonly columnIndex: number;
  
  constructor(rowIndex, columnIndex) {
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
  }
}
