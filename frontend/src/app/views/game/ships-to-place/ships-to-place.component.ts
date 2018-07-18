import {Component} from "@angular/core";
import {DragShipService} from "../../../services/drag-ship/drag-ship.service";
import {AvailableShips} from "../../../models/domain/available-ships";

@Component({
             selector: 'app-ships-to-place',
             templateUrl: './ships-to-place.component.html',
             styleUrls: ['./ships-to-place.component.css']
           })
export class ShipsToPlaceComponent {
  
  availableShips: AvailableShips;
  
  constructor(public dragShipService: DragShipService) {
    this.availableShips = new AvailableShips();
  }
}
