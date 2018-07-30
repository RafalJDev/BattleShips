import {Component} from "@angular/core"
import {DragShipsToPlace} from "../../../services/drag-ship/drag-ship-ships-to-place.service"
import {AvailableShipsToPlace} from "../../../models/domain/available-ships-to-place"
import {ShipPlacementDataTransfer} from "../transfer-class/ship-placement-data-transfer"

@Component({
             selector: 'app-ships-to-place',
             templateUrl: './ships-to-place.component.html',
             styleUrls: ['./ships-to-place.component.css'],
           })
export class ShipsToPlaceComponent {
  
  availableShips: AvailableShipsToPlace
  
  shipDirectionHorizontal: boolean = true
  
  shipPlacementTransfer: ShipPlacementDataTransfer = ShipPlacementDataTransfer.getInstance()
  
  constructor(public dragShipService: DragShipsToPlace) {
    this.availableShips = this.shipPlacementTransfer.availableShips
  }
  
  changeShipsDirection() {
    this.shipDirectionHorizontal = !this.shipDirectionHorizontal
    this.shipPlacementTransfer.isDraggedShipHorizontal = this.shipDirectionHorizontal
  }
  
}
