import {Component, OnInit} from '@angular/core'
import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {ShipGenerator} from "../../../services/ship-generator/ship-generator.service"
import {BoardTransferSingelton} from "../transfer-class/board-transfer-singelton"
import {DragShipService} from "../../../services/drag-ship/drag-ship.service"

@Component({
             selector: 'app-placing-board',
             templateUrl: './placing-board.component.html',
             styleUrls: ['./placing-board.component.css'],
           })
export class PlacingBoardComponent implements OnInit {
  
  placedBoard: BoardOfCells
  
  constructor(private shipGenerator: ShipGenerator,public dragShipService: DragShipService) {
    this.shipGenerator = new ShipGenerator()
    
    this.generateBoardWithWater()
  }
  
  ngOnInit() {
  }
  
  generateBoardWithWater() {
    this.placedBoard = new BoardOfCells()
    this.placedBoard.generateBoardWithWater(10)
  }
  
  generateRandomBoard() {
    this.generateBoardWithWater()
    this.putShipsFromShipGenerator_random()
  }
  
  putShipsFromShipGenerator_random() {
    const shipArray = this.shipGenerator.generateShipsRandomly(this.placedBoard)
    
    let instance = BoardTransferSingelton.getInstance()
    instance.placedBoard = this.placedBoard
    instance.shipArray = shipArray
  }
  
}
