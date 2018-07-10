import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
             selector: 'app-player-board',
             templateUrl: './player-board.component.html',
             styleUrls: ['./player-board.component.css']
           })
export class PlayerBoardComponent implements OnInit {
  
  @Output()
  isFleetCorrectFromPlayer: EventEmitter<Boolean> = new EventEmitter<Boolean>();
  
  constructor() {
  }
  
  ngOnInit() {
  }
  
  startGameChangeFromPlayer(event) {
    this.isFleetCorrectFromPlayer.emit(event);
  }
  
}
