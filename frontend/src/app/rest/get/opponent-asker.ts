import {GetRequestExecutor} from "./get-request-executor";
import {PlayersService} from "../../services/players-service.service";
import {HttpClient} from "@angular/common/http";

export class OpponentAsker extends GetRequestExecutor {

  hostUrl: string = GetRequestExecutor.getHostString();

  constructor(httpClient: HttpClient, private playersService: PlayersService) {
    super(httpClient);
  }

  public getOpponentResult() {
    return this.get(this.hostUrl + "/game/opponent/result?player=" + this.playersService.whoami.name);

  }
}
