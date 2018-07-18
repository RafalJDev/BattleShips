export class IndexVerification {
  
  static isOneIndexNotInRangeForBoardSize(boardSize, rowIndex, columnIndex): boolean {
    return this.isNotInRangeForBoardSize(boardSize, rowIndex) || this.isNotInRangeForBoardSize(boardSize, columnIndex);
  }
  
  static isNotInRangeForBoardSize(boardSize: number, index: number): boolean {
    return !(index >= 0 && index < boardSize);
  }
  
  static isOneOfIndexesNotInRegularRange(rowIndex, columnIndex): boolean {
    return this.isOneIndexNotInRangeForBoardSize(10, rowIndex, columnIndex);
  }
  
  static isInRegularRange(index): boolean {
    return index >= 0 && index < 10;
  }
  
  private static isNotInRange(boardSize: number, index: number): boolean {
    return !(index >= 0 && index < boardSize);
  }
}
