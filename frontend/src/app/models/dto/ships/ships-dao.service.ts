import {Injectable} from "@angular/core";

@Injectable()
export class ShipsDaoService {
  
  constructor(public ships: string[]) {
  }
}
