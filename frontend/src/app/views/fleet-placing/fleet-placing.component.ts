import {Component, OnInit} from '@angular/core'
import {ShipSender} from "../../rest/post/ship-sender"
import {ShipArray} from "../../models/domain/ship/ship-array"
import {BoardTransferSingelton} from "./transfer-class/board-transfer-singelton"

@Component({
             selector: 'app-fleet-placing',
             templateUrl: './fleet-placing.component.html',
             styleUrls: ['./fleet-placing.component.css'],
           })
export class FleetPlacingComponent implements OnInit {
  
  shipResponse: string
  
  constructor(private shipSender: ShipSender) {
  }
  
  ngOnInit() {
  
  }
  
  captureShipResponse(event) {
    this.shipResponse = event
    console.log("Capture ship response: " + this.shipResponse)
  }
  
  isThereShipsToSend(): boolean {
    return !(BoardTransferSingelton.getInstance().shipArray == undefined)
  }
  
  sendShipsToValidateOnBackend() {
    
    let shipArray: ShipArray = BoardTransferSingelton.getInstance().shipArray
    
    const shipArrayJson = JSON.stringify(shipArray)
    
    console.log(shipArrayJson)
    
    this.shipSender.postShip(shipArrayJson)
        .then(result => {
          let shipResultResponse = result['result']
      
          console.log("shipResultResponse: " + shipResultResponse)
      
        })
  }
  
}
