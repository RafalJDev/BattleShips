import {HttpEvent} from "@angular/common/http";
import {StrategyOpponentShotResult} from "./strategy/strategy-opponent-shot-result";
import {StrategyOpponentHit} from "./strategy/strategy-opponent-hit";
import {StrategyOpponentMiss} from "./strategy/strategy-opponent-miss";
import {StrategyOpponentSunk} from "./strategy/strategy-opponent-sunk";
import {StrategyOpponentNoShot} from "./strategy/strategy-opponent-no-shot";
import {StrategyOpponentPlayerLoose} from "./strategy/strategy-opponent-player-loose";

export class OpponetShotResultFactory {
  
  static createStrategyForOpponentShotResultFromJson(opponentShotResponseJson: HttpEvent<string>): StrategyOpponentShotResult {
    
    const opponentShotResultMessage = opponentShotResponseJson["opponentShotResult"];
    
    let shipDtoJson = opponentShotResponseJson["opponentShotDTO"];
    
    let coordinateJson = shipDtoJson["shotCoordinate"];
    
    let rowIndex = coordinateJson["rowIndex"];
    let columnIndex = coordinateJson["columnIndex"];
    
    switch (opponentShotResultMessage) {
      case "ShotHit":
        return new StrategyOpponentHit(rowIndex, columnIndex);
      case "ShotMiss":
        return new StrategyOpponentMiss(rowIndex, columnIndex);
      case "ShotSunk":
        return new StrategyOpponentSunk(rowIndex, columnIndex);
      case "NoShoot":
        return new StrategyOpponentNoShot(rowIndex, columnIndex);
      case "ShotPlayerLoose":
        return new StrategyOpponentPlayerLoose(rowIndex, columnIndex);
      default:
        throw new Error("No such OpponentShotResultMessage, probably bug on backend, Message: "
          + opponentShotResultMessage);
    }
  }
}
