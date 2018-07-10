import {Injectable} from "@angular/core";
import {CoordinateRandomGenerator} from "./coordinate-generator/coordinate-random-generator.service";
import {ShipOnBoardPlacer} from "./ship-placer/ship-on-board-placer.service";
import {DirectionGenerator} from "./direction-generator/direction-generator.service";
import {ShipCreator} from "./ship-creator/ship-creator";
import {BoardOfCells} from "../../models/board/board-of-cells";
import {ShipArray} from "../../models/ship/ship-array";
import {ShipOnArrayPlacer} from "./ship-placer/ship-on-array-placer";
import {MastCount} from "../../models/mast/mast-count";

@Injectable()
export class ShipGenerator {
  
  readonly generatedShips: ShipArray;
  readonly coordinateRandomGenerator: CoordinateRandomGenerator;
  private arrayOfMastToGenerate: Array<MastCount>;
  
  constructor() {
    this.coordinateRandomGenerator = new CoordinateRandomGenerator();
    
    this.generatedShips = new ShipArray();
  
    this.fillArrayOfMastCount();
  }
  
  fillArrayOfMastCount() {
    this.arrayOfMastToGenerate = [
      new MastCount(4),
      new MastCount(3),
      new MastCount(3),
      new MastCount(2),
      new MastCount(2),
      new MastCount(2),
      new MastCount(1),
      new MastCount(1),
      new MastCount(1),
      new MastCount(1),
    ];
  }
  
  generateShipsManually() {
    this.generatedShips.addShipWithCoordinates(0, 0, 0, 1, 0, 2, 0, 3);
    this.generatedShips.addShipWithCoordinates(3, 0, 3, 1, 3, 2);
    this.generatedShips.addShipWithCoordinates(5, 0, 5, 1);
    this.generatedShips.addShipWithCoordinates(7, 0, 7, 1);
    this.generatedShips.addShipWithCoordinates(9, 0);
  }
  
  generateShipsRandomly(board: BoardOfCells): ShipArray {
    this.arrayOfMastToGenerate.forEach(mast => {
      const mastCount = mast.count;
      const isHorizontal = DirectionGenerator.isHorizontal();
  
      let coordinate = this.coordinateRandomGenerator.createHeadCoordinate(board, mastCount, isHorizontal);
      
      const ship = ShipCreator.createShipWithHeadCoordinate(coordinate, mastCount, isHorizontal);
  
      ShipOnArrayPlacer.placeShip(this.generatedShips, ship);
  
      ShipOnBoardPlacer.placeShip(board, ship);
    });
    return this.generatedShips;
  }
}
