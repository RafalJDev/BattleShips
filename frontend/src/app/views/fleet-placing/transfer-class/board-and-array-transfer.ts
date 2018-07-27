import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {ShipArray} from "../../../models/domain/ship/ship-array"

export class BoardAndArrayTransfer {
  
  placedBoard: BoardOfCells
  
  shipArray: ShipArray
  
  private static thisInstance: BoardAndArrayTransfer
  
  private constructor() {
  }
  
  public static getInstance(): BoardAndArrayTransfer {
    if (BoardAndArrayTransfer.thisInstance == undefined) {
      BoardAndArrayTransfer.thisInstance = new BoardAndArrayTransfer()
    }
    return BoardAndArrayTransfer.thisInstance
  }
  
}
