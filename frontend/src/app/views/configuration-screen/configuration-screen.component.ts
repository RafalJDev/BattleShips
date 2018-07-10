import {Component} from "@angular/core";
import {Player} from "../../models/player";

@Component({
             selector: 'app-configuration-screen',
             templateUrl: './configuration-screen.component.html',
             styleUrls: ['./configuration-screen.component.css']
           })
export class ConfigurationScreenComponent {
  
  public player: Player;
  
  constructor() {
    this.player = new Player('sth', -1);
  }
  
  setValue() {
    this.player.name = '  Nancy';
  }
  
  isThereInputFromPlayer(): boolean {
    return this.player.name != 'sth' &&
      this.player.roomNumber != -1;
  }
}
