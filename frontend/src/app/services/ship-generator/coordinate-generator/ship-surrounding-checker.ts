import {CoordinateChecker} from "./coordinate-checker.service"
import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {Coordinate} from "../../../models/domain/ship/coordinate/coordinate"
import {CoordinatesOnSurrounding} from "./coordinate-on-surronding"
import {IndexRandomGenerator} from "../index-random-generator/index-random-generator.service"
import {Injectable} from "@angular/core"

@Injectable()
export class ShipSurroundingChecker {
  
  indexRandomGenerator: IndexRandomGenerator
  coordinateChecker: CoordinateChecker
  
  constructor() {
    this.indexRandomGenerator = new IndexRandomGenerator()
    this.coordinateChecker = new CoordinateChecker(this.indexRandomGenerator)
  }
  
  public isThereShipInSurrounding(coordinate: Coordinate,
                                  board: BoardOfCells,
                                  mastCount: number,
                                  isHorizontal: boolean): boolean {
    let coordinates: CoordinatesOnSurrounding
    let i: number
    for (i = -1; i < mastCount + 1; i++) {
      coordinates =
        CoordinatesOnSurrounding.createCoordinatesForDirection(coordinate, i, isHorizontal)
      if (this.coordinateChecker.isThereShipOnCoordinateSurroundings(board, coordinates)) {
        return true
      }
    }
    return false
  }
}

