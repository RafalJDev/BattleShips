import {Injectable} from "@angular/core"
import {BoardOfCells} from "../../models/domain/board/board-of-cells"
import {Coordinate} from "../../models/domain/ship/coordinate/coordinate"
import {BoardAndArrayTransfer} from "../../views/fleet-placing/transfer-class/board-and-array-transfer"
import {ShipPlacementDataTransfer} from "../../views/fleet-placing/transfer-class/ship-placement-data-transfer"
import {Ship} from "../../models/domain/ship/ship"
import {ShipOnArrayPlacer} from "../ship-generator/ship-placer/ship-on-array-placer"

@Injectable()
export class DragPlacingBoard {
  
  shipPlacementDataTransfer: ShipPlacementDataTransfer = ShipPlacementDataTransfer.getInstance()
  
  boardAndArrayTransfer: BoardAndArrayTransfer = BoardAndArrayTransfer.getInstance()
  
  constructor() {
  }
  
  dragEnter($event, boardOfCells: BoardOfCells, rowIndex, columnIndex) {
    console.log("set to true")
    
    this.shipPlacementDataTransfer.isOverPlacingBoardComponent = true
    
    this.shipPlacementDataTransfer.overCellCoordinate = new Coordinate(rowIndex, columnIndex)
    
  }
  
  dragLeave($event, boardOfCells: BoardOfCells, rowIndex, columnIndex) {
    console.log("set to false")
    
  }
  
  dragStart($event, rowIndex, columnIndex) {
  
  }
  
  /*
  * Remove ship from board and place it on ships to place
  */
  dragEnd($event, rowIndex, columnIndex) {
    let coordinate = new Coordinate(rowIndex, columnIndex)
    
    let shipFromCurrentCell: Ship = this.boardAndArrayTransfer.shipArray.getShipThatContainsCoordinate(coordinate)
    
    shipFromCurrentCell.coordinates.forEach(coordinate => {
      this.boardAndArrayTransfer.placedBoard.replaceCurrentCellContentWithWater(coordinate.rowIndex,
                                                                                coordinate.columnIndex)
    })
    ShipOnArrayPlacer.removeShip(this.boardAndArrayTransfer.shipArray, shipFromCurrentCell)
    
    this.shipPlacementDataTransfer.availableShips.addShipWithMastCount(shipFromCurrentCell.getMastCount())
  
    this.shipPlacementDataTransfer.isOverShipsToPlaceComponent = false
  }
  
}
