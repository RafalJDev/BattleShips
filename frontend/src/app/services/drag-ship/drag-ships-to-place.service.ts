import {Injectable} from "@angular/core"
import {AvailableShipsToPlace} from "../../models/domain/available-ships-to-place"
import {ShipPlacementTransfer} from "../../views/fleet-placing/placing-board/placing-board.component"
import {IndexVerification} from "../verification/index-verification"
import {Ship} from "../../models/domain/ship/ship"
import {ShipCreator} from "../ship-generator/ship-creator/ship-creator"
import {ShipOnBoardPlacer} from "../ship-generator/ship-placer/ship-on-board-placer.service"

@Injectable()
export class DragShipsToPlace {
  
  shipPlacementTransfer: ShipPlacementTransfer = ShipPlacementTransfer.getInstance()
  
  constructor() {
  }
  
  dragStart($event, availableShips: AvailableShipsToPlace, arrayMastIndex: number, shipInArrayIndex: number) {
    // this.availableShips = availableShips
    console.log('Ship set')
    
    this.shipPlacementTransfer.nowDraggedShip = availableShips.getShipOnIndex(arrayMastIndex, shipInArrayIndex)
    
    // availableShips.removeShipWithIndexes(arrayMastIndex, shipInArrayIndex)
  }
  
  dragEnd($event, availableShips: AvailableShipsToPlace, arrayMastIndex: number, shipInArrayIndex: number) {
    console.log("in dragEnd before if")
    
    if (this.shipPlacementTransfer.isOnPlacingBaordComponent) {
      
      console.log("in isOnPlacingBaordComponent")
      
      if (this.canShipBeSetHere()) {
        
        console.log("in dragEnd after ifs")
        
        let coordinate = this.shipPlacementTransfer.overCellCoordinate
        let mastCount = this.shipPlacementTransfer.nowDraggedShip.getMastCount()
        let isHorizontal = this.shipPlacementTransfer.isDraggedShipHorizontal
        
        const ship: Ship = ShipCreator.createShipWithHeadCoordinate(coordinate, mastCount, isHorizontal)
        
        ShipOnBoardPlacer.placeShip(this.shipPlacementTransfer.placedBoard, ship)
        availableShips.removeShipWithIndexes(arrayMastIndex, shipInArrayIndex)
      }
    }
    
    this.shipPlacementTransfer.isOnPlacingBaordComponent = false
    this.shipPlacementTransfer.overCellCoordinate = null
  }
  
  canShipBeSetHere(): boolean {
    let boardOfCells = this.shipPlacementTransfer.placedBoard
    let rowIndex = this.shipPlacementTransfer.overCellCoordinate.rowIndex
    let columnIndex = this.shipPlacementTransfer.overCellCoordinate.columnIndex
    
    if (boardOfCells.isShipOnCell(rowIndex, columnIndex)) {
      return false
    }
    
    let validBoardSize = 10 - this.shipPlacementTransfer.nowDraggedShip.getMastCount() + 1
    if (this.shipPlacementTransfer.isDraggedShipHorizontal) {
      return IndexVerification.isInRangeForBoardSize(validBoardSize, columnIndex)
    } else {
      return IndexVerification.isInRangeForBoardSize(validBoardSize, rowIndex)
    }
  }
  
}
