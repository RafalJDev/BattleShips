import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {ShipGenerator} from "../../../services/ship-generator/ship-generator.service";
import {BoardOfCells} from "../../../models/domain/board/board-of-cells";
import {ShipArray} from "../../../models/domain/ship/ship-array";
import {ShipSender} from "../../../rest/post/ship-sender";
import {DragShipService} from "../../../services/drag-ship/drag-ship.service";

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
    this.generateRandomBoard();

    console.log("tryClick");
    this.fakeDiv.nativeElement.click();

    this.boardDivEmitter.emit(this.boardDiv);
  }

  generateRandomBoard() {
    this.playerBoard = new BoardOfCells();
    this.playerBoard.generateBoardWithWater(10);

    this.putShipsFromShipGenerator_random();
  }

  putShipsFromShipGenerator_random() {
    const shipArray = this.shipGenerator.generateShipsRandomly(this.playerBoard);

    this.testShipSending(shipArray);
  }

  testShipSending(shipArray: ShipArray) {

    const shipArrayJson = JSON.stringify(shipArray);

    console.log(shipArrayJson);

    this.shipSender.postShip(shipArrayJson)
      .then(result => {
        this.result2 = result['result'];
        if (this.result2 == 'true') {
          console.log("Emiting result");
        }
      });
  }

  takeJustBoardDiv(boardDiv: Element) {
    console.log("clickOnPlayerBoar");
    this.boardDiv = boardDiv;
  }
}
