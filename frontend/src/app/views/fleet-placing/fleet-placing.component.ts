import {Component, OnInit} from '@angular/core'
import {ShipSender} from "../../rest/post/ship-sender"
import {ShipArray} from "../../models/domain/ship/ship-array"
import {BoardAndArrayTransfer} from "./transfer-class/board-and-array-transfer"
import {Router} from "@angular/router"
import {GameStartAsker} from "../../rest/get/game-start-asker"

@Component({
             selector: 'app-fleet-placing',
             templateUrl: './fleet-placing.component.html',
             styleUrls: ['./fleet-placing.component.css'],
           })
export class FleetPlacingComponent implements OnInit {
  
  private shipResponse: string
  
  private shipResultBoolean: boolean
  
  constructor(private shipSender: ShipSender, private gameStartAsker: GameStartAsker, private router: Router) {
  }
  
  ngOnInit() {
  
  }
  
  captureShipResponse(event) {
    this.shipResponse = event
    console.log("Capture ship response: " + this.shipResponse)
  }
  
  isThereShipsToSend(): boolean {
    return !(BoardAndArrayTransfer.getInstance().shipArray == undefined)
  }
  
  sendShipsToValidateOnBackend() {
    let shipArray: ShipArray = BoardAndArrayTransfer.getInstance().shipArray
    const shipArrayJson = JSON.stringify(shipArray)
    
    console.log(shipArrayJson)
    
    this.shipSender.postShip(shipArrayJson)
        .then(result => {
          //todo extract to method
          console.log("shipResultResponse: " + result['result'])
      
          this.shipResultBoolean = this.shipSender.responseToBoolean(result)
      
          if (this.shipResultBoolean) {
            this.askIfCanStartGame()
          }
        })
  }
  
  private askIfCanStartGame() {
    this.gameStartAsker.getGameStartResult()
        .then(gameStartResult => {
          let startGameResultBoolean = this.gameStartAsker.responseToBoolean(gameStartResult)
      
          if (startGameResultBoolean) {
            this.router.navigate(['/game/board'])
          } else {
            this.router.navigate(['/waiting/opponent/fleet'])
          }
        })
  }
  
}
