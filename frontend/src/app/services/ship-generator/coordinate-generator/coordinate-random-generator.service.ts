import {Injectable} from "@angular/core"
import {IndexRandomGenerator} from "../index-random-generator/index-random-generator.service"
import {CoordinateChecker} from "./coordinate-checker.service"
import {CoordinatesOnSurrounding} from "./coordinate-on-surronding"
import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {Coordinate} from "../../../models/domain/ship/coordinate/coordinate"

@Injectable()
export class CoordinateRandomGenerator {
  
  indexRandomGenerator: IndexRandomGenerator;
  coordinateChecker: CoordinateChecker;
  
  constructor() {
    this.indexRandomGenerator = new IndexRandomGenerator();
    this.coordinateChecker = new CoordinateChecker(this.indexRandomGenerator);
  }
  
  createHeadCoordinate(board: BoardOfCells,
                       mastCount: number,
                       isHorizontal): Coordinate {
    let rowIndex;
    let columnIndex;
    
    let coordinate;
    const boardSize = board.getBoardSize();
    
    do {
      rowIndex = this.indexRandomGenerator.generateValid_Y(boardSize, isHorizontal, mastCount);
      columnIndex = this.indexRandomGenerator.generateValid_X(boardSize, isHorizontal, mastCount);
      coordinate = new Coordinate(rowIndex, columnIndex);
    } while (this.isThereShipInSurrounding(coordinate, board, mastCount, isHorizontal));
    
    return new Coordinate(rowIndex, columnIndex);
  }
  
  public isThereShipInSurrounding(coordinate: Coordinate,
                                   board: BoardOfCells,
                                   mastCount: number,
                                   isHorizontal: boolean): boolean {
    let coordinates: CoordinatesOnSurrounding;
    let i: number;
    for (i = -1; i < mastCount + 1; i++) {
      coordinates =
        CoordinatesOnSurrounding.createCoordinatesForDirection(coordinate, i, isHorizontal);
      if (this.coordinateChecker.isThereShipOnCoordinateSurroundings(board, coordinates)) {
        return true;
      }
    }
    return false;
  }
}


