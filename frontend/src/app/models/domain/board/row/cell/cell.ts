import {Content} from "./content/content";
import {ContentShip} from "./content/content-ship";
import {ContentWater} from "./content/content-water";

export class Cell {
  
  private content: Content;
  
  constructor(content: Content) {
    this.content = content;
  }
  
  static ofWater(): Cell {
    return new Cell(new ContentWater());
  }
  
  static ofShip(): Cell {
    return new Cell(new ContentShip());
  }
  
  isShipHere(): boolean {
    return this.content.isShip();
  }
  
  isWaterHere(): boolean {
    return this.content.isWater();
  }
}
