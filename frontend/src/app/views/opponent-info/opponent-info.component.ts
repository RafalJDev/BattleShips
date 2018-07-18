import { Component, OnInit } from '@angular/core';
import {Player} from "../../models/player";

@Component({
  selector: 'app-opponent-info',
  templateUrl: './opponent-info.component.html',
  styleUrls: ['./opponent-info.component.css']
})
export class OpponentInfoComponent implements OnInit {

  player: Player;

  constructor() {}

  ngOnInit() {}

}
