import {Injectable} from "@angular/core"
import {Coordinate} from "../../models/domain/ship/coordinate/coordinate"
import {BoardAndArrayTransfer} from "../../views/fleet-placing/transfer-class/board-and-array-transfer"
import {ShipPlacementDataTransfer} from "../../views/fleet-placing/transfer-class/ship-placement-data-transfer"
import {Ship} from "../../models/domain/ship/ship"
import {ShipOnArrayPlacer} from "../ship-generator/ship-placer/ship-on-array-placer"
import {ShipCreator} from "../ship-generator/ship-creator/ship-creator"
import {ShipOnBoardPlacer} from "../ship-generator/ship-placer/ship-on-board-placer.service"
import {IndexVerification} from "../verification/index-verification"
import {ShipSurroundingChecker} from "../ship-generator/coordinate-generator/ship-surrounding-checker"
import {ConfigurationShip} from "../../models/domain/configuration-ship"

@Injectable()
export class DragShipPlacingBoard {
  
  shipPlacementDataTransfer: ShipPlacementDataTransfer = ShipPlacementDataTransfer.getInstance()
  
  boardAndArrayTransfer: BoardAndArrayTransfer = BoardAndArrayTransfer.getInstance()
  
  constructor(public shipSurroundingChecker: ShipSurroundingChecker) {
  }
  
  dragEnter($event, rowIndex, columnIndex) {
    console.log("dragEnter, rowIndex: " + rowIndex + " columnIndex:" + columnIndex)
    if (this.boardAndArrayTransfer.placedBoard.isShipOnCell(rowIndex, columnIndex)) {
      this.shipPlacementDataTransfer.isOverPlacingBoardComponent = false
    } else {
      this.shipPlacementDataTransfer.isOverPlacingBoardComponent = true
      
      this.shipPlacementDataTransfer.overCellCoordinate = new Coordinate(rowIndex, columnIndex)
    }
  }
  
  dragLeave($event, rowIndex, columnIndex) {
  }
  
  dragExit($event, rowIndex, columnIndex) {
    console.log("dragExit")
  }
  
  dragStart($event, rowIndex, columnIndex) {
    this.shipPlacementDataTransfer.isOverPlacingBoardComponent = true
    this.shipPlacementDataTransfer.isOverShipsToPlaceComponent = false
    
    console.log("dragStart: " + "rowIndex: " + rowIndex + " columnIndex:" + columnIndex)
    
    let currentDraggedShip: Ship = this.boardAndArrayTransfer.shipArray.getShipThatContainsIndexes(
      rowIndex, columnIndex)
    
    console.log("dragStart:currentDraggedShip " + "rowIndex: " + currentDraggedShip.coordinates.forEach(
      coordinate => console.log("coordinate.rowIndex: " + coordinate.rowIndex + " coordinate.columnIndex:" + coordinate.columnIndex)))
    
    this.shipPlacementDataTransfer.nowDraggedConfigurationShip = new ConfigurationShip(
      currentDraggedShip.getMastCount())
    this.shipPlacementDataTransfer.nowDraggedShip = currentDraggedShip
  }
  
  /*
  * Remove ship from board and place it on ships to place
  */
  dragEnd($event, rowIndex, columnIndex) {
    console.log("dragEnd, rowIndex: " + rowIndex + " columnIndex:" + columnIndex)
    
    let shipArray = this.boardAndArrayTransfer.shipArray
    let placedBoard = this.boardAndArrayTransfer.placedBoard
    
    if (this.shipPlacementDataTransfer.isOverShipsToPlaceComponent) {
      let shipFromCurrentCell: Ship = shipArray.getShipThatContainsIndexes(rowIndex,
                                                                           columnIndex)
      
      shipFromCurrentCell.coordinates.forEach(coordinate => {
        placedBoard.replaceCurrentCellContentWithWater(coordinate.rowIndex,
                                                       coordinate.columnIndex)
      })
      ShipOnArrayPlacer.removeShip(shipArray, shipFromCurrentCell)
      
      this.shipPlacementDataTransfer.availableShips.addShipWithMastCount(shipFromCurrentCell.getMastCount())
    } else if (this.shipPlacementDataTransfer.isOverPlacingBoardComponent) {
      
      console.log("After isOverPlacingBoardComponent")
      
      let coordinate = this.shipPlacementDataTransfer.overCellCoordinate
      let mastCount = this.shipPlacementDataTransfer.nowDraggedConfigurationShip.getMastCount()
      let isHorizontal = this.shipPlacementDataTransfer.nowDraggedShip.isShipOnHorizontal()
      if (this.canShipBeSetHere(mastCount, isHorizontal)) {
        
        console.log("dragEnd after canShipBeSetHere")
        
        const ship: Ship = ShipCreator.createShipWithHeadCoordinate(coordinate, mastCount, isHorizontal)
        
        let previousShip: Ship = this.shipPlacementDataTransfer.nowDraggedShip
        ShipOnArrayPlacer.removeShip(shipArray, previousShip)
        ShipOnBoardPlacer.removeShipFromBoard(placedBoard, previousShip)
        
        ShipOnArrayPlacer.placeShip(shipArray, ship)
        ShipOnBoardPlacer.placeShip(placedBoard, ship)
        
      }
    }
    
    this.shipPlacementDataTransfer.isOverShipsToPlaceComponent = false
    this.shipPlacementDataTransfer.isOverPlacingBoardComponent = false
    
    this.shipPlacementDataTransfer.nowDraggedShip = null
    this.shipPlacementDataTransfer.nowDraggedConfigurationShip = null
    
    this.shipPlacementDataTransfer.overCellCoordinate = null
  }
  
  private canShipBeSetHere(mastCount: number, isHorizontal: boolean): boolean {
    let placedBoard = this.boardAndArrayTransfer.placedBoard
    let overCellCoordinate = this.shipPlacementDataTransfer.overCellCoordinate
    let rowIndex = overCellCoordinate.rowIndex
    let columnIndex = overCellCoordinate.columnIndex
    
    console.log("drag ship-placing 105" + " overCellCoordinate.rowIndex: " + overCellCoordinate.rowIndex
      + " overCellCoordinate.columnIndex: " + overCellCoordinate.columnIndex)
    console.log("drag ship-placing 105" + " nowDraggedShip.rowIndex: " + this.shipPlacementDataTransfer.nowDraggedShip.coordinates[0].rowIndex
      + " nowDraggedShip.columnIndex: " + this.shipPlacementDataTransfer.nowDraggedShip.coordinates[0].columnIndex)
    
    ShipOnBoardPlacer.removeShipFromBoard(placedBoard, this.shipPlacementDataTransfer.nowDraggedShip)
    if (this.shipSurroundingChecker.isThereShipInSurrounding(overCellCoordinate, placedBoard,
                                                             mastCount, isHorizontal)) {
      ShipOnBoardPlacer.placeShip(placedBoard, this.shipPlacementDataTransfer.nowDraggedShip)
      return false
    }
    
    console.log("drag ship-placing 113")
    
    ShipOnBoardPlacer.placeShip(placedBoard, this.shipPlacementDataTransfer.nowDraggedShip)
    
    let validBoardSize = 10 - this.shipPlacementDataTransfer.nowDraggedConfigurationShip.getMastCount() + 1
    if (isHorizontal) {
      return IndexVerification.isInRangeForBoardSize(validBoardSize, columnIndex)
    } else {
      return IndexVerification.isInRangeForBoardSize(validBoardSize, rowIndex)
    }
  }
  
}
