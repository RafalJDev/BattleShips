import {Component, OnInit} from "@angular/core";

@Component({
             selector: 'app-start-game-button',
             templateUrl: './start-game-button.component.html',
             styleUrls: ['./start-game-button.component.css']
           })
export class StartGameButtonComponent implements OnInit {
  
  //@Input() startGame = false;
  astartGame = false;
  
  constructor() {
  
  }
  
  ngOnInit() {
  }
  
}
