import {Injectable} from "@angular/core";

@Injectable()
export class DirectionGenerator {
  
  constructor() {
  }
  
  public static isHorizontal() {
    return this.randomBoolean();
  }
  
  private static randomBoolean() {
    return Math.random() >= 0.5;
  }
}
