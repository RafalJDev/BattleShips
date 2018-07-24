import {GetRequestExecutor} from "./get-request-executor";
import {PlayersService} from "../../services/players-service.service";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class FirstRoundAsker extends GetRequestExecutor {
  
  hostUrl: string = GetRequestExecutor.getHostString();
  
  constructor(httpClient: HttpClient, private playersService: PlayersService) {
    super(httpClient);
  }
  
  public getFirstRoundResult() {
    //todo REST API turn to round
    return this.get(this.hostUrl + "/game/player/isTurn?playerName=" + this.playersService.whoami.name);
    
  }
}
