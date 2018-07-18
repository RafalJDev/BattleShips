import {Content} from "./content";

export class ContentWater implements Content {
  isWater(): boolean {
    return true;
  }
  
  isShip(): boolean {
    return false;
  }
}
