import {Injectable} from "@angular/core"
import {AvailableShipsToPlace} from "../../models/domain/available-ships-to-place"
import {IndexVerification} from "../verification/index-verification"
import {Ship} from "../../models/domain/ship/ship"
import {ShipCreator} from "../ship-generator/ship-creator/ship-creator"
import {ShipOnBoardPlacer} from "../ship-generator/ship-placer/ship-on-board-placer.service"
import {ShipSurroundingChecker} from "../ship-generator/coordinate-generator/ship-surrounding-checker"
import {ShipPlacementDataTransfer} from "../../views/fleet-placing/transfer-class/ship-placement-data-transfer"
import {ShipOnArrayPlacer} from "../ship-generator/ship-placer/ship-on-array-placer"
import {BoardAndArrayTransfer} from "../../views/fleet-placing/transfer-class/board-and-array-transfer"

@Injectable()
export class DragShipsToPlace {
  
  shipPlacementDataTransfer: ShipPlacementDataTransfer = ShipPlacementDataTransfer.getInstance()
  
  boardAndArrayTransfer: BoardAndArrayTransfer = BoardAndArrayTransfer.getInstance()
  
  constructor(public shipSurroundingChecker: ShipSurroundingChecker) {
  }
  
  dragStart($event, availableShips: AvailableShipsToPlace, arrayMastIndex: number, shipInArrayIndex: number) {
    this.shipPlacementDataTransfer.nowDraggedConfigurationShip = availableShips.getShipOnIndex(arrayMastIndex, shipInArrayIndex)
  }
  
  dragEnd($event, availableShips: AvailableShipsToPlace, arrayMastIndex: number, shipInArrayIndex: number) {
    console.log("in dragEnd before if")
    
    if (this.shipPlacementDataTransfer.isOverPlacingBoardComponent) {
      
      console.log("in isOverPlacingBoardComponent")
      
      let coordinate = this.shipPlacementDataTransfer.overCellCoordinate
      let mastCount = this.shipPlacementDataTransfer.nowDraggedConfigurationShip.getMastCount()
      let isHorizontal = this.shipPlacementDataTransfer.isDraggedShipHorizontal
      if (this.canShipBeSetHere(mastCount, isHorizontal)) {
        
        console.log("in dragEnd after ifs")
        
        const ship: Ship = ShipCreator.createShipWithHeadCoordinate(coordinate, mastCount, isHorizontal)
        
        ShipOnArrayPlacer.placeShip(this.boardAndArrayTransfer.shipArray, ship)
        ShipOnBoardPlacer.placeShip(this.boardAndArrayTransfer.placedBoard, ship)
        
        availableShips.removeShipWithIndexes(arrayMastIndex, shipInArrayIndex)
      }
    }
    
    this.shipPlacementDataTransfer.isOverPlacingBoardComponent = false
    this.shipPlacementDataTransfer.overCellCoordinate = null
  }
  
  dragEnter() {
    this.shipPlacementDataTransfer.isOverShipsToPlaceComponent = true
    this.shipPlacementDataTransfer.isOverPlacingBoardComponent = false
  }
  
  private canShipBeSetHere(mastCount: number, isHorizontal: boolean): boolean {
    let boardOfCells = this.boardAndArrayTransfer.placedBoard
    let overCellCoordinate = this.shipPlacementDataTransfer.overCellCoordinate
    let rowIndex = overCellCoordinate.rowIndex
    let columnIndex = overCellCoordinate.columnIndex
    
    if (this.shipSurroundingChecker.isThereShipInSurrounding(overCellCoordinate, boardOfCells,
                                                             mastCount, isHorizontal)) {
      return false
    }
    
    let validBoardSize = 10 - this.shipPlacementDataTransfer.nowDraggedConfigurationShip.getMastCount() + 1
    if (this.shipPlacementDataTransfer.isDraggedShipHorizontal) {
      return IndexVerification.isInRangeForBoardSize(validBoardSize, columnIndex)
    } else {
      return IndexVerification.isInRangeForBoardSize(validBoardSize, rowIndex)
    }
  }
  
}
