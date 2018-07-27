import {Injectable} from "@angular/core"
import {ShipPlacementTransfer} from "../../views/fleet-placing/placing-board/placing-board.component"
import {BoardOfCells} from "../../models/domain/board/board-of-cells"
import {Coordinate} from "../../models/domain/ship/coordinate/coordinate"

@Injectable()
export class DragPlacingBoard {
  
  shipPlacementTransfer: ShipPlacementTransfer = ShipPlacementTransfer.getInstance()
  
  constructor() {
  }
  
  dragEnter($event, boardOfCells: BoardOfCells, rowIndex, columnIndex) {
    console.log("set to true")
    
    this.shipPlacementTransfer.isOnPlacingBaordComponent = true
    
    this.shipPlacementTransfer.overCellCoordinate = new Coordinate(rowIndex, columnIndex)
    
    // if (this.canShipBeSetHere(boardOfCells, rowIndex, columnIndex)) {
    //   console.log("in dragEnter after if")
    //   let coordinate = new Coordinate(rowIndex, columnIndex)
    //   let mastCount = this.shipPlacementTransfer.nowDraggedShip.getMastCount()
    //   let isHorizontal = this.shipPlacementTransfer.isDraggedShipHorizontal
    //
    //   const ship: Ship = ShipCreator.createShipWithHeadCoordinate(coordinate, mastCount, isHorizontal)
    //
    //   ShipOnBoardPlacer.placeShip(this.shipPlacementTransfer.placedBoard, ship)
    // }
  }
  
  dragLeave($event, boardOfCells: BoardOfCells, rowIndex: number, columnIndex: number) {
    console.log("set to false")
    
    
    // console.log("rowIndex: " + rowIndex)
    // console.log("columnIndex: " + columnIndex)
    //
    // let coordinate = new Coordinate(rowIndex, columnIndex)
    // let mastCount = this.shipPlacementTransfer.nowDraggedShip.getMastCount()
    // let isHorizontal = this.shipPlacementTransfer.isDraggedShipHorizontal
    //
    // const ship: Ship = ShipCreator.createShipWithHeadCoordinate(coordinate, mastCount, isHorizontal)
    //
    // ShipOnBoardPlacer.removeShipFromBoard(this.shipPlacementTransfer.placedBoard, ship)
  }
  
  dragStart($event, rowIndex, columnIndex) {
  
  }
  
  dragEnd($event, rowIndex, columnIndex) {
  
  }
  
}
