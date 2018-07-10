import {Content} from "./content";

export class ContentShip implements Content {
  isWater(): boolean {
    return false;
  }
  
  isShip(): boolean {
    return true;
  }
}
