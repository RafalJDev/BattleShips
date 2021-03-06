import {Coordinate} from "../../../models/domain/ship/coordinate/coordinate"
import {AvailableShipsToPlace} from "../../../models/domain/available-ships-to-place"
import {ConfigurationShip} from "../../../models/domain/configuration-ship"
import {Ship} from "../../../models/domain/ship/ship"

export class ShipPlacementDataTransfer {
  
  availableShips: AvailableShipsToPlace = new AvailableShipsToPlace()
  
  nowDraggedConfigurationShip: ConfigurationShip
  
  isDraggedShipHorizontal: boolean = true
  
  isOverPlacingBoardComponent: boolean = false
  
  overCellCoordinate: Coordinate = null
  
  isOverShipsToPlaceComponent: boolean = false
  
  nowDraggedShip:Ship = null
  
  private static thisReference: ShipPlacementDataTransfer
  
  static getInstance(): ShipPlacementDataTransfer {
    if (this.thisReference == null) {
      this.thisReference = new ShipPlacementDataTransfer()
    }
    return this.thisReference
  }
  
}
