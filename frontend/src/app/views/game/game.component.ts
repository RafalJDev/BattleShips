import {Component, OnInit} from "@angular/core"
import {MessageTransfer} from "./message-transfer/message-transfer"

@Component({
             selector: 'app-game',
             templateUrl: './game.component.html',
             styleUrls: ['./game.component.css'],
           })
export class GameComponent implements OnInit {
  
  playerBoardDiv: Element
  
  messageTransfer: MessageTransfer = MessageTransfer.getInstance()
  
  constructor() {
  }
  
  ngOnInit() {
  }
  
  isEndOfGame():boolean {
    return this.messageTransfer.playerBoardMessage == "YOU ARE THE WINNER!" ||
      this.messageTransfer.opponentBoardMessage == "YOU LOOSE THE GAME!!!"
  }
  
  capturePlayerBoardDiv(event) {
    this.playerBoardDiv = event
    console.log("Capture gamecomponent" + this.playerBoardDiv)
  }
}
