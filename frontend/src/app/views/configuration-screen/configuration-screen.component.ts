import {Component} from "@angular/core";
import {Player} from "../../models/player";
import {LoginRequestSender} from "../../rest/post/login-request";
import {PlayersService} from "../../services/players-service.service";
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute} from '@angular/router';

@Component({
             selector: 'app-configuration-screen',
             templateUrl: './configuration-screen.component.html',
             styleUrls: ['./configuration-screen.component.css'],
           })
export class ConfigurationScreenComponent {
  
  player: Player;
  
  selectedLanguage: string;
  
  constructor(public loginRequestExecutor: LoginRequestSender,
              private playersService: PlayersService,
              private translate: TranslateService,
              private route: ActivatedRoute) {
    this.player = new Player('sth');
    
    let pathname = window.location.pathname.toString();
    
    console.log("in configuration construcor length: " + pathname.toString());
    
    this.selectedLanguage = pathname.split("/")[2];
    this.translate.use(this.selectedLanguage);
    
    console.log("after configuration construcor ");
  }
  
  login() {
    this.playersService.whoami = this.player;
    const playerInJSON = JSON.stringify(this.player);
    this.loginRequestExecutor.postLogin(playerInJSON)
        .then(result => {
          this.player.token = result['result'];
  
          console.log("Value in post: " + this.player.token);
        });
  }
  
  isThereInputFromPlayer(): boolean {
    return this.player.name != "sth";
  }
  
  isThereResponseFromBackend(): boolean {
    return this.player.token != undefined;
  }
}
