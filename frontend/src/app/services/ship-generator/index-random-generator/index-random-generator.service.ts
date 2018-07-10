import {Injectable} from "@angular/core";

@Injectable()
export class IndexRandomGenerator {
  
  constructor() {
  }
  
  generateValid_X(boardSize: number, isHorizontal: boolean, mastCount: number): number {
    let validBoardSize = isHorizontal ?
                         (boardSize - mastCount) : boardSize;
    
    return this.generateRandomValidIndex(validBoardSize, isHorizontal);
  }
  
  generateValid_Y(boardSize: number, isHorizontal: boolean, mastCount: number): number {
    let validBoardSize = isHorizontal ?
                         boardSize : (boardSize - mastCount);
    
    return this.generateRandomValidIndex(validBoardSize, isHorizontal);
  }
  
  isOneIndexNotInRange(boardSize, rowIndex, columnIndex): boolean {
    return this.isNotInRange(boardSize, rowIndex) || this.isNotInRange(boardSize, columnIndex);
  }
  
  private generateRandomValidIndex(boardSize: number, isHorizontal): number {
    let index = -1;
    while (this.isNotInRange(boardSize, index)) {
      index = Math.floor(Math.random() * (boardSize + 1));
    }
    return index;
  }
  
  private isNotInRange(boardSize: number, index: number): boolean {
    return !(index >= 0 && index < boardSize);
  }
}
