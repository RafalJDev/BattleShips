import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core'
import {ShipGenerator} from "../../../services/ship-generator/ship-generator.service"
import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {ShipSender} from "../../../rest/post/ship-sender"
import {DragShipService} from "../../../services/drag-ship/drag-ship.service"
import {BoardTransferSingelton} from "../../fleet-placing/transfer-class/board-transfer-singelton"

@Component({
  selector: 'app-player-board',
  templateUrl: './player-board.component.html',
  styleUrls: ['./player-board.component.css']
})
export class PlayerBoardComponent implements OnInit {

  public result2: string;

  playerBoard: BoardOfCells;

  @ViewChild("fakeDiv") fakeDiv: ElementRef;

  boardDiv: Element;

  @Output()
  boardDivEmitter = new EventEmitter<Element>();

  constructor(private shipGenerator: ShipGenerator, private shipSender: ShipSender,
              public dragShipService: DragShipService) {
    this.shipGenerator = new ShipGenerator();
  }

  ngOnInit(): void {
    //todo delete if singelton works
    // this.generateRandomBoard();
    let instance = BoardTransferSingelton.getInstance()
    this.playerBoard = instance.placedBoard
  
    console.log("tryClick");
    this.fakeDiv.nativeElement.click();

    this.boardDivEmitter.emit(this.boardDiv);
  }

  takeJustBoardDiv(boardDiv: Element) {
    console.log("clickOnPlayerBoar");
    this.boardDiv = boardDiv;
  }
}
