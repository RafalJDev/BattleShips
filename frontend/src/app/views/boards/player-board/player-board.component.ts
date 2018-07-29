import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core'
import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {DragShipsToPlace} from "../../../services/drag-ship/drag-ship-ships-to-place.service"
import {BoardAndArrayTransfer} from "../../fleet-placing/transfer-class/board-and-array-transfer"
import {MessageTransfer} from "../../game/message-transfer/message-transfer"

@Component({
             selector: 'app-player-board',
             templateUrl: './player-board.component.html',
             styleUrls: ['./player-board.component.css'],
           })
export class PlayerBoardComponent implements OnInit {
  
  public result2: string
  
  playerBoard: BoardOfCells
  
  @ViewChild("fakeDiv")
  fakeDiv: ElementRef
  
  boardDiv: Element
  
  @Output()
  boardDivEmitter = new EventEmitter<Element>()
  
  messageTransfer: MessageTransfer = MessageTransfer.getInstance()
  
  
  constructor(public dragShipService: DragShipsToPlace) {
  }
  
  ngOnInit(): void {
    //todo delete if singelton works
    // this.generateRandomBoard();
    let instance = BoardAndArrayTransfer.getInstance()
    this.playerBoard = instance.placedBoard
    
    console.log("tryClick")
    this.fakeDiv.nativeElement.click()
    
    this.boardDivEmitter.emit(this.boardDiv)
  }
  
  takeJustBoardDiv(boardDiv: Element) {
    console.log("clickOnPlayerBoar")
    this.boardDiv = boardDiv
  }
}
