import {Component, OnInit} from '@angular/core'
import {BoardOfCells} from "../../../models/domain/board/board-of-cells"
import {ShipGenerator} from "../../../services/ship-generator/ship-generator.service"
import {BoardTransferSingelton} from "../transfer-class/board-transfer-singelton"
import {AvailableShipsToPlace} from "../../../models/domain/available-ships-to-place"
import {ShipArray} from "../../../models/domain/ship/ship-array"
import {DragPlacingBoard} from "../../../services/drag-ship/drag-placing-board"
import {ConfigurationShip} from "../../../models/domain/configuration-ship"
import {Coordinate} from "../../../models/domain/ship/coordinate/coordinate"

@Component({
             selector: 'app-placing-board',
             templateUrl: './placing-board.component.html',
             styleUrls: ['./placing-board.component.css'],
           })
export class PlacingBoardComponent implements OnInit {
  
  placedBoard: BoardOfCells
  
  shipPlacementTransfer: ShipPlacementTransfer
  
  constructor(private shipGenerator: ShipGenerator, public dragPlacingBoard: DragPlacingBoard) {
    this.shipGenerator = new ShipGenerator()
    this.shipPlacementTransfer = ShipPlacementTransfer.getInstance()
    
    this.generateBoardWithWater()
    
    this.shipPlacementTransfer.availableShips.generateDefaultFullFleet()
  }
  
  ngOnInit() {
  }
  
  generateBoardWithWater() {
    this.placedBoard = new BoardOfCells()
    this.placedBoard.generateBoardWithWater(10)
    
    console.log("generateBoardWithWater: " + this.shipPlacementTransfer)
    this.shipPlacementTransfer.placedBoard = this.placedBoard
  }
  
  generateRandomBoard() {
    this.generateBoardWithWater()
    this.putShipsFromShipGenerator_random()
  }
  
  putShipsFromShipGenerator_random() {
    const shipArray: ShipArray = this.shipGenerator.generateShipsRandomly(this.placedBoard)
    
    let instance = BoardTransferSingelton.getInstance()
    instance.placedBoard = this.placedBoard
    instance.shipArray = shipArray
    
    this.shipPlacementTransfer.availableShips.generateNoFleet()
    this.shipPlacementTransfer.placedBoard = this.placedBoard
  }
  
}

export class ShipPlacementTransfer {
  
  isAvailableShipPlaced: boolean = false
  
  availableShips: AvailableShipsToPlace = new AvailableShipsToPlace()
  
  nowDraggedShip: ConfigurationShip
  
  isDraggedShipHorizontal: boolean = true
  
  placedBoard: BoardOfCells = new BoardOfCells()
  
  isOnPlacingBaordComponent: boolean = false
  
  overCellCoordinate: Coordinate = null
  
  private static thisReference: ShipPlacementTransfer
  
  static getInstance(): ShipPlacementTransfer {
    if (this.thisReference == null) {
      this.thisReference = new ShipPlacementTransfer()
    }
    return this.thisReference
  }
  
}
