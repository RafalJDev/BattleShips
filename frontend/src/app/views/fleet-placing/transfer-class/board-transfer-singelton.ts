import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {ShipArray} from "../../../models/domain/ship/ship-array"

export class BoardTransferSingelton {
  
  placedBoard: BoardOfCells
  
  shipArray: ShipArray
  
  private static thisInstance: BoardTransferSingelton
  
  private constructor() {
  }
  
  public static getInstance(): BoardTransferSingelton {
    if (BoardTransferSingelton.thisInstance == undefined) {
      BoardTransferSingelton.thisInstance = new BoardTransferSingelton()
    }
    return BoardTransferSingelton.thisInstance
  }
  
}
