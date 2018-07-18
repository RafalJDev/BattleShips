import {Injectable} from "@angular/core";
import {IndexRandomGenerator} from "../index-random-generator/index-random-generator.service";
import {CoordinatesOnSurrounding} from "./coordinate-on-surronding";
import {BoardOfCells} from "../../../models/domain/board/board-of-cells";
import {IndexVerification} from "../../verification/index-verification";

@Injectable()
export class CoordinateChecker {
  
  indexGenerator: IndexRandomGenerator;
  
  constructor(indexGenerator: IndexRandomGenerator) {
    this.indexGenerator = indexGenerator;
  }
  
  isThereShipOnCoordinateSurroundings(board: BoardOfCells,
                                      coordinates: CoordinatesOnSurrounding): boolean {
    return this.isThereShipOnUpperCoordinate(board, coordinates) ||
      this.isThereShipOnCenterCoordinate(board, coordinates) ||
      this.isThereShipOnLowerCoordinate(board, coordinates);
    
  }
  
  private isThereShipOnUpperCoordinate(board: BoardOfCells,
                                       coordinates: CoordinatesOnSurrounding): boolean {
    return this.isThereShipOnCoordinate(coordinates.upperRowIndex,
                                        coordinates.upperColumnIndex,
                                        board);
  }
  
  private isThereShipOnCenterCoordinate(board: BoardOfCells,
                                        coordinates: CoordinatesOnSurrounding): boolean {
    return this.isThereShipOnCoordinate(coordinates.centerRowIndex,
                                        coordinates.centerColumnIndex,
                                        board);
  }
  
  private isThereShipOnLowerCoordinate(board: BoardOfCells,
                                       coordinates: CoordinatesOnSurrounding): boolean {
    return this.isThereShipOnCoordinate(coordinates.lowerRowIndex,
                                        coordinates.lowerColumnIndex,
                                        board);
  }
  
  private isThereShipOnCoordinate(rowIndex, columnIndex, board: BoardOfCells): boolean {
  
    if (IndexVerification.isOneIndexNotInRangeForBoardSize(board.getBoardSize(), rowIndex, columnIndex)) {
      return false;
    }
    
    return board.isShipOnCell(rowIndex, columnIndex);
  };
}
