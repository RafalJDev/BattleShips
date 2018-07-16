import {Injectable} from "@angular/core";
import {ShipSender} from "../../rest/post/ship-sender";
import {ShotSender} from "../../rest/post/shot-sender";
import {ResultHit} from "../../models/dto/shot/result/result-hit";

@Injectable()
export class OpponentBoardHandler {

  isPlayerTurn: boolean = true;

  constructor(public shipSender: ShipSender, public shotSender: ShotSender) {
  }

  handleShotClick(boardDiv: Element, rowIndex, columnIndex) {
    console.log("click!");

    if (this.isPlayerTurn) {

      const resultHit = new ResultHit();
      resultHit.handleResult(boardDiv, rowIndex, columnIndex);

    }
  }

}
