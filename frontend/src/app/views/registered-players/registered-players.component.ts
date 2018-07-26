import {Component, OnDestroy, OnInit} from '@angular/core';
import {PlayersService} from "../../services/players-service.service";
import {Player} from "../../models/player";

@Component({
  selector: 'app-registered-players',
  templateUrl: './registered-players.component.html',
  styleUrls: ['./registered-players.component.css']
})
export class RegisteredPlayersComponent implements OnInit, OnDestroy {

  players: Player[];
  tempPlayers: Player[];

  timer: any;

  constructor(public playersService: PlayersService) {
    this.playersService.getPlayers()
      .subscribe(playerArr => {
        this.players = playerArr;
      })
  }

  ngOnDestroy() {
    clearInterval(this.timer);
  }

  ngOnInit() {
    this.timer = setInterval(() =>
      this.playersService.getPlayers()
        .subscribe(playerArr => {
          this.players = playerArr;
        }), 2000);
  }
  
}
