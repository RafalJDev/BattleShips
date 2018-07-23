import {StrategyShotResult} from "./strategy/strategy-shot-result";
import {HttpEvent} from "@angular/common/http";
import {StrategyHit} from "./strategy/strategy-hit";
import {StrategyMiss} from "./strategy/strategy-miss";
import {StrategySunk} from "./strategy/strategy-sunk";
import {StrategyPlayerWin} from "./strategy/strategy-player-win";

export class ShotResultFactory {
  
  static createStrategyForShotResultFromJson(shotResponseJson: HttpEvent<string>): StrategyShotResult {
    
    const shotResultMessage = shotResponseJson["resultMessage"];
    let strategyShotResult: StrategyShotResult;
    
    switch (shotResultMessage) {
      case "ResultHit":
        strategyShotResult = new StrategyHit();
        break;
      case "ResultMiss":
        strategyShotResult = new StrategyMiss();
        break;
      case "PlayerWon":
        strategyShotResult = new StrategyPlayerWin();
        break;
      case "ResultSunk":
        strategyShotResult = new StrategySunk();
        break;
      default:
        throw new Error("Some Exception With Response from backend");
    }
    
    return strategyShotResult;
  }
}
