import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Player} from "../models/player";
import {RegisteredPlayers} from "../rest/get/registered-players";
import {of} from "rxjs/observable/of";

@Injectable()
export class PlayersService {

  whoami:Player;

  constructor(private registeredPlayers: RegisteredPlayers) {
    this.whoami = new Player("sth");
  }

  public getPlayers(): Observable<Player[]> {
    let players: Player[];
    players = [];
    this.registeredPlayers.getRegistered().then(response => {
      let i = 0;
      while (response[i] !== undefined) {
        players.push(new Player(response[i]['name']));
        i++;
      }
    });
    return of(players);
  }
}
