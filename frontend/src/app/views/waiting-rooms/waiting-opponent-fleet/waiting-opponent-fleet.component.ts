import {Component, OnDestroy, OnInit} from '@angular/core'
import {GameStartAsker} from "../../../rest/get/game-start-asker"
import {Router} from "@angular/router"

@Component({
             selector: 'app-waiting-opponent-fleet',
             templateUrl: './waiting-opponent-fleet.component.html',
             styleUrls: ['./waiting-opponent-fleet.component.css'],
           })
export class WaitingOpponentFleetComponent implements OnInit, OnDestroy {
  
  timer: any
  
  constructor(public gameStartAsker: GameStartAsker, private router: Router) {
  }
  
  ngOnDestroy() {
    clearInterval(this.timer)
  }
  
  ngOnInit() {
    this.timer = setInterval(() =>
                               this.gameStartAsker.getGameStartResult()
                                   .then(gameStartResult => {
                                           let startGameResultBoolean = this.gameStartAsker.responseToBoolean(gameStartResult)
                                           if (startGameResultBoolean) {
                                             this.router.navigate(['/game/board'])
                                           }
                                         },
                                   ), 2000)
  }
  
}
