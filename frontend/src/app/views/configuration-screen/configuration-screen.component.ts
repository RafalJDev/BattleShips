import {Component} from "@angular/core";
import {Player} from "../../models/player";
import {LoginRequestSender} from "../../rest/post/login-request";

@Component({
  selector: 'app-configuration-screen',
  templateUrl: './configuration-screen.component.html',
  styleUrls: ['./configuration-screen.component.css']
})
export class ConfigurationScreenComponent {

  player: Player;

    constructor(public loginRequestExecutor: LoginRequestSender) {
    this.player = new Player('sth');
  }

  login() {
    const playerInJSON = JSON.stringify(this.player);
    this.loginRequestExecutor.postLogin(playerInJSON)
      .then(result => {
        this.player.token = result['result'];
        console.log(this.player.token);
      })
  }

  isThereInputFromPlayer(): boolean {
    return this.player.name != 'sth';
  }
}
