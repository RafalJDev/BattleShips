import {Injectable} from "@angular/core";
import {IndexVerification} from "../../verification/index-verification";

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

  private generateRandomValidIndex(boardSize: number, isHorizontal): number {
    let index = -1;
    while (IndexVerification.isNotInRangeForBoardSize(boardSize, index)) {
      index = Math.floor(Math.random() * (boardSize + 1));
    }
    return index;
  }

}
