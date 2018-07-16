export interface Content {
  isWater(): boolean;

  isShip(): boolean;
}

export enum ContentEnum {

  WATER = "water",
  SHIP = "",
  MISS = "",
  HITTED = "",
  SUNK = ""

}
