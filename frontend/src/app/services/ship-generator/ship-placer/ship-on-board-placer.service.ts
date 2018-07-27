import {Injectable} from "@angular/core"
import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {Ship} from "../../../models/domain/ship/ship"
import {ShipArray} from "../../../models/domain/ship/ship-array"

@Injectable()
export class ShipOnBoardPlacer {
  
  constructor() {
  }
  
  public static placeShip(board: BoardOfCells, ship: Ship) {
    this.putEachMastOnBoard(board, ship)
  }
  
  public static removeShipFromBoard(board: BoardOfCells, ship: Ship) {
    const coordinates = ship.coordinates
    coordinates.forEach(coordinate => {
      const rowIndex = coordinate.rowIndex
      const columnIndex = coordinate.columnIndex
  
      console.log("rowIndex: "+rowIndex)
      console.log("columnIndex: "+columnIndex)
  
      board.putWaterOnCell(rowIndex, columnIndex)
    })
  }
  
  public static placeAllGeneratedShipsOnBoard(board: BoardOfCells, generatedShips: ShipArray) {
    const ships = generatedShips.shipArray
    ships.forEach(ship => {
      this.putEachMastOnBoard(board, ship)
    })
  }
  
  private static putEachMastOnBoard(board: BoardOfCells, ship: Ship) {
    const coordinates = ship.coordinates
    coordinates.forEach(coordinate => {
      const rowIndex = coordinate.rowIndex
      const columnIndex = coordinate.columnIndex
      
      board.putShipOnCell(rowIndex, columnIndex)
    })
  }
  
}
