import {Component} from "@angular/core"
import {DragShipsToPlace} from "../../../services/drag-ship/drag-ships-to-place.service"
import {AvailableShipsToPlace} from "../../../models/domain/available-ships-to-place"
import {ShipPlacementTransfer} from "../placing-board/placing-board.component"

@Component({
             selector: 'app-ships-to-place',
             templateUrl: './ships-to-place.component.html',
             styleUrls: ['./ships-to-place.component.css'],
           })
export class ShipsToPlaceComponent {
  
  availableShips: AvailableShipsToPlace
  
  shipDirectionHorizontal: boolean = true
  
  shipPlacementTransfer: ShipPlacementTransfer
  
  constructor(public dragShipService: DragShipsToPlace) {
    this.shipPlacementTransfer = ShipPlacementTransfer.getInstance()
    this.availableShips = this.shipPlacementTransfer.availableShips
  }
  
  changeShipsDirection() {
    this.shipDirectionHorizontal = !this.shipDirectionHorizontal
    this.shipPlacementTransfer.isDraggedShipHorizontal = this.shipDirectionHorizontal
  }
  
}
