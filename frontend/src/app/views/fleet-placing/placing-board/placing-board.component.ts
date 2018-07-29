import {Component, OnInit} from '@angular/core'
import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {ShipGenerator} from "../../../services/ship-generator/ship-generator.service"
import {BoardAndArrayTransfer} from "../transfer-class/board-and-array-transfer"
import {ShipArray} from "../../../models/domain/ship/ship-array"
import {DragShipPlacingBoard} from "../../../services/drag-ship/drag-ship-placing-board"
import {ShipPlacementDataTransfer} from "../transfer-class/ship-placement-data-transfer"

@Component({
             selector: 'app-placing-board',
             templateUrl: './placing-board.component.html',
             styleUrls: ['./placing-board.component.css'],
           })
export class PlacingBoardComponent implements OnInit {
  
  placedBoard: BoardOfCells
  
  shipPlacementTransfer: ShipPlacementDataTransfer = ShipPlacementDataTransfer.getInstance()
  
  boardAndArrayTransfer: BoardAndArrayTransfer = BoardAndArrayTransfer.getInstance()
  
  constructor(private shipGenerator: ShipGenerator, public dragPlacingBoard: DragShipPlacingBoard) {
    this.generateBoardWithWater()
    
    this.shipPlacementTransfer.availableShips.generateDefaultFullFleet()
  }
  
  ngOnInit() {
  }
  
  generateBoardWithWater() {
    this.placedBoard = new BoardOfCells()
    this.placedBoard.generateBoardWithWater(10)
    
    console.log("generateBoardWithWater: " + this.shipPlacementTransfer)
    this.boardAndArrayTransfer.placedBoard = this.placedBoard
    this.boardAndArrayTransfer.shipArray = new ShipArray()
  }
  
  generateRandomBoard() {
    this.generateBoardWithWater()
    this.putShipsFromShipGenerator_random()
  }
  
  putShipsFromShipGenerator_random() {
    const shipArray: ShipArray = this.shipGenerator.generateShipsRandomly(this.placedBoard)
    
    this.boardAndArrayTransfer.placedBoard = this.placedBoard
    this.boardAndArrayTransfer.shipArray = shipArray
    
    this.shipPlacementTransfer.availableShips.generateNoFleet()
    this.boardAndArrayTransfer.placedBoard = this.placedBoard
  }
  
}
