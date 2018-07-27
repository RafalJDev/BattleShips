import {Component} from "@angular/core"
import {Player} from "../../../models/player"
import {LoginRequestSender} from "../../../rest/post/login-request"
import {PlayersService} from "../../../services/player-identification/players-service.service"
import {ActivatedRoute} from '@angular/router'

@Component({
             selector: 'app-configuration-screen',
             templateUrl: './player-name-configuration.component.html',
             styleUrls: ['./player-name-configuration.component.css'],
           })
export class PlayerNameConfiguration {
  
  player: Player;
  
  selectedLanguage: string;
  
  beforeResponse: boolean = true;
  
  constructor(public loginRequestExecutor: LoginRequestSender,
              private playersService: PlayersService,
              private route: ActivatedRoute) {
    this.player = new Player('sth');
  }
  
  login() {
    this.playersService.whoami = this.player;
    const playerInJSON = JSON.stringify(this.player);
    this.loginRequestExecutor.postLogin(playerInJSON)
        .then(result => {
          this.player.token = result['result'];
  
          console.log("Value in post: " + this.player.token);
          this.beforeResponse = false;
        });
  }
  
  isThereInputFromPlayer(): boolean {
    return this.player.name != "sth" && this.beforeResponse;
  }
  
}
