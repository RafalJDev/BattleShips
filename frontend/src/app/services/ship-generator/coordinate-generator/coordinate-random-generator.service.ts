import {Injectable} from "@angular/core"
import {IndexRandomGenerator} from "../index-random-generator/index-random-generator.service"
import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {Coordinate} from "../../../models/domain/ship/coordinate/coordinate"
import {ShipSurroundingChecker} from "./ship-surrounding-checker"

@Injectable()
export class CoordinateRandomGenerator {
  
  constructor(public indexRandomGenerator: IndexRandomGenerator,
              public shipSurroundingChecker: ShipSurroundingChecker) {
  }
  
  createHeadCoordinate(board: BoardOfCells,
                       mastCount: number,
                       isHorizontal): Coordinate {
    let rowIndex
    let columnIndex
    
    let coordinate
    const boardSize = board.getBoardSize()
    
    do {
      rowIndex = this.indexRandomGenerator.generateValid_Y(boardSize, isHorizontal, mastCount)
      columnIndex = this.indexRandomGenerator.generateValid_X(boardSize, isHorizontal, mastCount)
      coordinate = new Coordinate(rowIndex, columnIndex)
    } while (this.shipSurroundingChecker.isThereShipInSurrounding(coordinate, board, mastCount, isHorizontal))
    
    return new Coordinate(rowIndex, columnIndex)
  }
  
}
